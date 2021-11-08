package dev.panda.material;

import dev.panda.utilities.Server;
import org.bukkit.Material;

/**
 * Created by Risas
 * Project: PandaLib
 * Date: 08-11-2021 - 16:20
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public enum CompatibleMaterial {

    HUMAN_SKULL("SKULL_ITEM", "LEGACY_SKULL_ITEM"),
    RED_WOOL("WOOL", "RED_WOOL"),
    ORANGE_WOOL("WOOL", "ORANGE_WOOL"),
    YELLOW_WOOL("WOOL", "YELLOW_WOOL"),
    DARK_GREEN_WOOL("WOOL", "GREEN_WOOL"),
    GREEN_WOOL("WOOL", "LIME_WOOL"),
    DARK_BLUE_WOOL("WOOL", "BLUE_WOOL"),
    BLUE_WOOL("WOOL", "LIGHT_BLUE_WOOL"),
    AQUA_WOOL("WOOL", "CYAN_WOOL"),
    PURPLE_WOOL("WOOL", "PURPLE_WOOL"),
    PINK_WOOL("WOOL", "PINK_WOOL"),
    WHITE_WOOL("WOOL", "WHITE_WOOL"),
    DARK_GRAY_WOOL("WOOL", "GRAY_WOOL"),
    GRAY_WOOL("WOOL", "LIGHT_GRAY_WOOL"),
    BLACK_WOOL("WOOL", "BLACK_WOOL"),
    SNOW_BALL("SNOW_BALL", "SNOWBALL"),
    CARPET("CARPET", "LEGACY_CARPET");

    private final String material8;
    private final String material12;
    private final String material13;

    CompatibleMaterial(String material8, String material12, String material13) {
        this.material8 = material8;
        this.material12 = material12;
        this.material13 = material13;
    }

    CompatibleMaterial(String material8, String material13) {
        this(material8, material13, material13);
    }

    CompatibleMaterial(String material13) {
        this(null, null, material13);
    }

    public Material getMaterial() {
        if (Server.SERVER_VERSION_INT == 8) {
            return material8 == null ? Material.valueOf("SKULL_ITEM") : Material.valueOf(material8);
        }
        else if (Server.SERVER_VERSION_INT > 9 && Server.SERVER_VERSION_INT < 13) {
            return material12 == null ? Material.valueOf("LEGACY_SKULL_ITEM") : Material.valueOf(material12);
        }
        else if (Server.SERVER_VERSION_INT >= 13) {
            return material13 == null ? Material.valueOf("LEGACY_SKULL_ITEM") : Material.valueOf(material13);
        }
        else {
            return null;
        }
    }
}
