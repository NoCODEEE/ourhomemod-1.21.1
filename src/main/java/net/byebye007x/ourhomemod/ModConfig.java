package net.byebye007x.ourhomemod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ModConfig {
    public static Identifier flightIngredient = Identifier.of("minecraft", "shulker_shell");
    public static int potionStackSize = 32;

    public static void load() {
        Path configPath = FabricLoader.getInstance()
                .getConfigDir()
                .resolve("ourhomemod.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject json = new JsonObject();

        if (Files.exists(configPath)) {
            try (Reader r = Files.newBufferedReader(configPath)) {
                json = JsonParser.parseReader(r).getAsJsonObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Add new Field here
        if (json.has("flightIngredient")) {
            String raw = json.get("flightIngredient").getAsString();
            Identifier parsed = Identifier.tryParse(raw);
            flightIngredient = Objects.requireNonNullElseGet(parsed, () -> Identifier.of("minecraft", "shulker_shell"));
        } else {
            json.addProperty("flightIngredient", flightIngredient.toString());
        }

        if (json.has("potionStackSize")) {
            potionStackSize = json.get("potionStackSize").getAsInt();
        } else {
            json.addProperty("potionStackSize", potionStackSize);
        }


        try (Writer w = Files.newBufferedWriter(configPath)) {
            gson.toJson(json, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
