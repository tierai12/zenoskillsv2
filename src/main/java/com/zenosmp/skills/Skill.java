package com.zenosmp.skills;

import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * The 10 skills of Zeno SMP. Declaration order matters:
 * the first 5 are LIFE, the last 5 are DESTRUCTION (used for the menu layout).
 */
public enum Skill {

    // ───────────────────────────── LIFE ─────────────────────────────
    LIFEBLOOM("lifebloom", "Lifebloom", Category.LIFE, Material.GLISTERING_MELON_SLICE,
            "Your body heals itself.",
            new String[]{"Permanent Regeneration I"},
            "Bloom",
            new String[]{"Instantly heal 8 hearts and", "gain Regeneration II for 6s"},
            35, Color.fromRGB(120, 255, 150)),

    TITAN_HEART("titan_heart", "Titan Heart", Category.LIFE, Material.HEART_OF_THE_SEA,
            "A heart that refuses to stop.",
            new String[]{"+5 bonus hearts (30 max health)"},
            "Titan's Resolve",
            new String[]{"Gain 8 absorption hearts for 20s", "and Resistance I for 10s"},
            60, Color.fromRGB(255, 60, 80)),

    PHOENIX_SOUL("phoenix_soul", "Phoenix Soul", Category.LIFE, Material.TOTEM_OF_UNDYING,
            "Burn. Fall. Rise again.",
            new String[]{"Fire Resistance",
                    "Fatal damage revives you at 50% health",
                    "and blasts nearby foes (10 min cooldown)"},
            "Ascension",
            new String[]{"Launch skyward in a blaze of fire", "with Slow Falling for 8s"},
            20, Color.fromRGB(255, 150, 30)),

    GAIA_BLESSING("gaia_blessing", "Gaia's Blessing", Category.LIFE, Material.SPORE_BLOSSOM,
            "The forest walks with you.",
            new String[]{"Jump Boost I", "Hunger drains 50% slower"},
            "Nature's Embrace",
            new String[]{"Heal yourself and players within 8 blocks", "for 4 hearts + Regeneration II (8s)"},
            45, Color.fromRGB(130, 255, 100)),

    VAMPIRIC_HEART("vampiric_heart", "Vampiric Heart", Category.LIFE, Material.FERMENTED_SPIDER_EYE,
            "Their life becomes yours.",
            new String[]{"Heal 12% of melee damage you deal"},
            "Blood Frenzy",
            new String[]{"For 10s lifesteal rises to 50%", "plus Strength I and Speed I"},
            50, Color.fromRGB(200, 20, 50)),

    // ──────────────────────────── DESTRUCTION ────────────────────────────
    INFERNO("inferno", "Inferno", Category.DESTRUCTION, Material.BLAZE_POWDER,
            "Fire answers only to you.",
            new String[]{"Fire Resistance", "Melee hits ignite enemies"},
            "Inferno Burst",
            new String[]{"Detonate a ring of fire (7 blocks):", "8 damage, ignite and knockback"},
            30, Color.fromRGB(255, 110, 20)),

    TEMPEST("tempest", "Tempest", Category.DESTRUCTION, Material.WIND_CHARGE,
            "The wind is your weapon.",
            new String[]{"Speed I", "No fall damage"},
            "Cyclone",
            new String[]{"Hurl everything within 8 blocks skyward", "for 4 damage; you gain Speed II (6s)"},
            25, Color.fromRGB(200, 240, 255)),

    THUNDER_GOD("thunder_god", "Thunder God", Category.DESTRUCTION, Material.LIGHTNING_ROD,
            "Call down the sky's fury.",
            new String[]{"Haste I", "12% chance to smite on hit"},
            "Thunder Judgment",
            new String[]{"Strike up to 5 enemies within 12 blocks", "with lightning for 9 damage each"},
            40, Color.fromRGB(255, 240, 90)),

    FROST_WRATH("frost_wrath", "Frost Wrath", Category.DESTRUCTION, Material.BLUE_ICE,
            "Winter obeys your will.",
            new String[]{"Immune to freezing", "Melee hits slow enemies"},
            "Absolute Zero",
            new String[]{"Freeze enemies within 8 blocks:", "Slowness IV (5s) + 4 damage"},
            35, Color.fromRGB(140, 220, 255)),

    VOID_WALKER("void_walker", "Void Walker", Category.DESTRUCTION, Material.ENDER_EYE,
            "Step between the cracks of reality.",
            new String[]{"Night Vision", "15% chance to inflict Wither II on hit"},
            "Void Rift",
            new String[]{"Teleport up to 10 blocks forward; foes", "at arrival take 6 damage + Darkness (5s)"},
            28, Color.fromRGB(150, 60, 255));

    private final String id;
    private final String displayName;
    private final Category category;
    private final Material icon;
    private final String tagline;
    private final String[] passive;
    private final String activeName;
    private final String[] activeLines;
    private final int cooldownSeconds;
    private final Color accent;

    Skill(String id, String displayName, Category category, Material icon, String tagline,
          String[] passive, String activeName, String[] activeLines, int cooldownSeconds, Color accent) {
        this.id = id;
        this.displayName = displayName;
        this.category = category;
        this.icon = icon;
        this.tagline = tagline;
        this.passive = passive;
        this.activeName = activeName;
        this.activeLines = activeLines;
        this.cooldownSeconds = cooldownSeconds;
        this.accent = accent;
    }

    public String id() { return id; }
    public String displayName() { return displayName; }
    public Category category() { return category; }
    public Material icon() { return icon; }
    public String tagline() { return tagline; }
    public String[] passive() { return passive; }
    public String activeName() { return activeName; }
    public String[] activeLines() { return activeLines; }
    public int cooldownSeconds() { return cooldownSeconds; }
    public Color accent() { return accent; }

    public static Skill byId(String id) {
        if (id == null) return null;
        for (Skill s : values()) {
            if (s.id.equalsIgnoreCase(id)) return s;
        }
        return null;
    }

    public static List<Skill> in(Category category) {
        List<Skill> out = new ArrayList<>();
        for (Skill s : values()) {
            if (s.category == category) out.add(s);
        }
        return out;
    }
}
