package com.rpg.services;

import com.rpg.models.Campaign;
import com.rpg.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CampaignServiceTest {

    private CampaignService campaignService;

    @BeforeEach
    void setUp() {
        campaignService = new CampaignService();
    }

    @Test
    void testSaveAndLoadCampaign() {
        Campaign campaign = new Campaign("TestCampaign", "TestMaster");
        campaignService.saveCampaign(campaign);

        Campaign loadedCampaign = campaignService.loadCampaign("TestCampaign");
        assertNotNull(loadedCampaign);
        assertEquals("TestCampaign", loadedCampaign.getName());
        assertEquals("TestMaster", loadedCampaign.getMaster());
    }

    @Test
    void testAddPlayerToCampaign() {
        Campaign campaign = new Campaign("TestCampaign", "TestMaster");
        campaignService.saveCampaign(campaign);

        Player player = new Player("TestPlayer");
        campaignService.addPlayerToCampaign("TestCampaign", player);

        Campaign loadedCampaign = campaignService.loadCampaign("TestCampaign");
        assertEquals(1, loadedCampaign.getPlayers().size());
        assertEquals("TestPlayer", loadedCampaign.getPlayers().get(0).getName());
    }
}