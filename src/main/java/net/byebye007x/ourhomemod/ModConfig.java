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

    public static void load() {
        Path configPath = FabricLoader.getInstance()
                .getConfigDir()
                .resolve("ourhomemod.json");

        // Write default if missing
        if (!Files.exists(configPath)) {
            JsonObject def = new JsonObject();
            def.addProperty("flight_ingredient", flightIngredient.toString());
            try (Writer w = Files.newBufferedWriter(configPath)) {
                new GsonBuilder().setPrettyPrinting().create().toJson(def, w);
            } catch (IOException e) { e.printStackTrace(); }
        }

        // Read it back
        try (Reader r = Files.newBufferedReader(configPath)) {
            JsonObject json = JsonParser.parseReader(r).getAsJsonObject();
            if (json.has("flight_ingredient")) {
                String raw = json.get("flight_ingredient").getAsString();
                Identifier parsed = Identifier.tryParse(raw);
                // fallback
                flightIngredient = Objects.requireNonNullElseGet(parsed, () -> Identifier.of("minecraft", "shulker_shell"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
