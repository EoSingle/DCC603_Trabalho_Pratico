package com.rpg.services;

import com.rpg.models.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private GameService gameService;
    private CampaignService campaignService;

    @BeforeEach
    void setUp() {
        campaignService = new CampaignService();
        gameService = new GameService();
    }

    @Test
    void testCreateCampaign() {
        Scanner scanner = new Scanner("TestCampaign\n");
        gameService.createCampaign("TestMaster", scanner);
        Campaign campaign = campaignService.loadCampaign("TestCampaign");
        assertNotNull(campaign);
        assertEquals("TestCampaign", campaign.getName());
        assertEquals("TestMaster", campaign.getMaster());
    }
}