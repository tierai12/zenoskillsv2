package com.zenosmp.skills;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.concurrent.ThreadLocalRandom;

public final class PlayerListener implements Listener {

    private final ZenoSkillsPlugin plugin;
    private final SkillManager mgr;

    public PlayerListener(ZenoSkillsPlugin plugin) {
        this.plugin = plugin;
        this.mgr = plugin.skills();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        mgr.tickPassives(p);
        if (mgr.hasSkill(p)) return;

        // small delay so the client has finished loading before the cinematic starts
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!p.isOnline() || mgr.hasSkill(p)) return;
            if (plugin.getConfig().getBoolean("intro-animation", true)) {
                IntroAnimation.play(plugin, p, () -> {
                    if (p.isOnline() && !mgr.hasSkill(p)) SkillMenu.openMain(p);
                });
            } else {
                SkillMenu.openMain(p);
            }
        }, 30L);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        mgr.endIntro(e.getPlayer());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!p.isOnline()) return;
            mgr.tickPassives(p);
            AttributeInstance max = p.getAttribute(Attribute.MAX_HEALTH);
            if (max != null) p.setHealth(max.getValue());
        }, 2L);
    }

    /** Shift + F casts the active ability. */
    @EventHandler(ignoreCancelled = true)
    public void onSwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        if (!p.isSneaking()) return;
        if (!mgr.hasSkill(p)) return;
        e.setCancelled(true);
        mgr.cast(p);
    }

    /** Gaia's Blessing: hunger drains 50% slower. */
    @EventHandler(ignoreCancelled = true)
    public void onFood(FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;
        if (mgr.getSkill(p) != Skill.GAIA_BLESSING) return;
        if (e.getFoodLevel() < p.getFoodLevel() && ThreadLocalRandom.current().nextBoolean()) {
            e.setCancelled(true);
        }
    }
}
