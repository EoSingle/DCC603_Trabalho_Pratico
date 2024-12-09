// CampaignTest.java
package com.rpg.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampaignTest {

    private Campaign campaign;

    @BeforeEach
    void setUp() {
        campaign = new Campaign("TestCampaign", "TestMaster");
    }

    @Test
    void testCampaignCreation() {
        assertEquals("TestCampaign", campaign.getName());
        assertEquals("TestMaster", campaign.getMaster());
        assertTrue(campaign.getPlayers().isEmpty());
    }

    @Test
    void testAddPlayer() {
        Player player = new Player("TestPlayer");
        campaign.addPlayer(player);
        assertEquals(1, campaign.getPlayers().size());
        assertEquals("TestPlayer", campaign.getPlayers().get(0).getName());
    }
}