package com.rpg.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.rpg.models.Campaign;
import com.rpg.models.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
//import java.io.Reader;
import java.io.File;
import java.io.FileReader;

public class CampaignService {
    private static final String CAMPAIGN_FOLDER = "src/main/resources/campaigns/";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public CampaignService() {
        createCampaignFolder();
    }

    // Cria a pasta campaigns/ caso ela não exista
    private void createCampaignFolder() {
        File folder = new File(CAMPAIGN_FOLDER);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Pasta 'campaigns/' criada com sucesso.");
            } else {
                System.err.println("Erro ao criar a pasta 'campaigns/'.");
            }
        }
    }

    // Salva a campanha em um arquivo JSON
    public void saveCampaign(Campaign campaign) {
        String filePath = CAMPAIGN_FOLDER + campaign.getName() + ".json";
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(campaign, writer);
            System.out.println("Campanha '" + campaign.getName() + "' salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar a campanha: " + e.getMessage());
        }
    }

    // Carrega a campanha a partir de um arquivo JSON
    public Campaign loadCampaign(String name) {
        String filePath = CAMPAIGN_FOLDER + name + ".json";
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Erro: Campanha '" + name + "' não encontrada.");
            return null;
        }

        try (FileReader reader = new FileReader(file)) {
            Campaign campaign = gson.fromJson(reader, Campaign.class);
            //System.out.println("Campanha '" + name + "' carregada com sucesso.");
            return campaign;
        } catch (IOException e) {
            System.err.println("Erro ao carregar a campanha: " + e.getMessage());
            return null;
        }
    }

    // Retorna uma lista de campanhas que o usuário é mestre O(n)
    public List<Campaign> listCampaignsMaster(String user) {
        List<Campaign> userCampaigns = new ArrayList<>();
        File folder = new File(CAMPAIGN_FOLDER);
        File[] files = folder.listFiles();
        if (files == null) {
            return userCampaigns;
        }
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".json")) {
                String campaignName = fileName.substring(0, fileName.length() - 5);
                Campaign campaign = loadCampaign(campaignName);
                if (campaign != null && campaign.getMaster().equals(user)) {
                    userCampaigns.add(campaign);
                }
            }
        }
        return userCampaigns;
    }

    // Retorna uma lista de campanhas que o usuário é jogador O(n*m)
    public List<Campaign> listCampaignsPlayer(String user) {
        List<Campaign> userCampaigns = new ArrayList<>();
        File folder = new File(CAMPAIGN_FOLDER);
        File[] files = folder.listFiles();
        if (files == null) {
            return userCampaigns;
        }
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".json")) {
                String campaignName = fileName.substring(0, fileName.length() - 5);
                Campaign campaign = loadCampaign(campaignName);
                if (campaign != null) {
                    List<Player> players = campaign.getPlayers();
                    for (Player player : players) {
                        if (player.getName().equals(user)) {
                            userCampaigns.add(campaign);
                            break;
                        }
                    }
                }
            }
        }
        return userCampaigns;
    }

    // Adiciona um jogador a uma campanha
    public void addPlayerToCampaign(String campaignName, Player player) {
        Campaign campaign = loadCampaign(campaignName);
        if (campaign == null) {
            System.err.println("Erro: Campanha '" + campaignName + "' não encontrada.");
            return;
        }
        campaign.addPlayer(player);
        saveCampaign(campaign);
    }
}
