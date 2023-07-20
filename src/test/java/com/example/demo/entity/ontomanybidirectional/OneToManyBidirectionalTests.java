package com.example.demo.entity.ontomanybidirectional;

import com.example.demo.entity.onetomanybidirectional.Player;
import com.example.demo.entity.onetomanybidirectional.Stat;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.StatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OneToManyBidirectionalTests {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    StatRepository statRepository;

    @BeforeEach
    public void beforeEachTest() {
        statRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @Test
    public void testSavePlayer() {
        Player player = new Player();
        player.setName("Cosmo");
        playerRepository.save(player);

        Assert.isTrue(player.getId() != null, "Player id is not null after persist.");

        Player player1 = playerRepository.findByNameIgnoreCase("cosmo");

        Assert.isTrue(player1.getId() != null, "Player query success and id is not null.");
    }

    @Test
    public void testSaveStat() {
        Stat stat = new Stat();
        stat.setName("Wins");
        statRepository.save(stat);

        Assert.isTrue(stat.getId() != null, "Stat id is not null after persist.");

        Stat stat1 = statRepository.findAll().get(0);

        Assert.isTrue(stat1.getId() != null, "Stat query success and id is not null.");
    }

    @Test
    public void testGetStatsFromPlayer() {
        Player player = new Player();
        player.setName("Cosmo");

        Stat stat = new Stat();
        stat.setName("Wins");
        stat.setPlayer(player);

        Stat stat1 = new Stat();
        stat1.setName("Losses");
        stat1.setPlayer(player);

        List<Stat> stats = new ArrayList<>();
        stats.add(stat);
        stats.add(stat1);

        player.setStats(stats);
        playerRepository.save(player);

        Player player1 = playerRepository.findByNameIgnoreCase("cosmo");
        List<Stat> stats1 = player1.getStats();

        Assert.isTrue(stats1 != null && stats1.size() == 2, "Get player stats from player.");
    }

    @Test
    public void getPlayerFromStat() {
        Player player = new Player();
        player.setName("Cosmo");

        Stat stat = new Stat();
        stat.setName("Wins");
        stat.setPlayer(player);

        Stat stat1 = new Stat();
        stat1.setName("Losses");
        stat1.setPlayer(player);

        List<Stat> stats = new ArrayList<>();
        stats.add(stat);
        stats.add(stat1);

        player.setStats(stats);
        playerRepository.save(player);

        List<Stat> stats1 = statRepository.findByPlayerNameIgnoreCase("cosmo");

        Assert.isTrue(stats1 != null && stats1.size() > 0, "Find stats by player name.");

        Player player1 = stats1.get(0).getPlayer();

        Assert.isTrue(player1.getId() != null, "Player fetched from Stat.");
    }

    @Test
    public void testDeleteStatsByCascade() {
        Player player = new Player();
        player.setName("Cosmo");

        Stat stat = new Stat();
        stat.setName("Wins");
        stat.setPlayer(player);

        Stat stat1 = new Stat();
        stat1.setName("Losses");
        stat1.setPlayer(player);

        List<Stat> stats = new ArrayList<>();
        stats.add(stat);
        stats.add(stat1);

        player.setStats(stats);
        playerRepository.save(player);

        Assert.isTrue(statRepository.findAll() != null && statRepository.findAll().size() > 0, "Verify that stats are present.");

        playerRepository.delete(player);

        Assert.isTrue(playerRepository.findAll() != null && playerRepository.findAll().size() == 0, "Verify player was deleted.");
        Assert.isTrue(statRepository.findAll() != null && statRepository.findAll().size() == 0, "Verify stats were deleted by cascade.");
    }
}
