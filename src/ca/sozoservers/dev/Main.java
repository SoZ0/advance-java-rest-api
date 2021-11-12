package ca.sozoservers.dev;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import ca.sozoservers.dev.api.SpellAPI;
import ca.sozoservers.dev.database.Database;
import ca.sozoservers.dev.models.SpellModel;
import ca.sozoservers.dev.server.HTTPServer;
import ca.sozoservers.dev.spells.Spell;

public class Main {

    public Main(){}

    public static void main(String[] args) throws IOException{
        File databasePath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getFile()+"data.db");
        new Database(databasePath);
        if(!Database.createDatabase()){
            System.out.println("Database failed to create");
            System.exit(0);
        }
        Database.createTable(new SpellModel());
        populateDatabase();
        HTTPServer server = new HTTPServer(8080, 0);
        server.createResource("/api/spells", new SpellAPI());
        server.start();
    }

    public static void populateDatabase(){
        SpellModel model = new SpellModel();
        String dummyData[][] = {{"Acid Splash","Conjuration","1 Action","60 Feet","Instantaneous","V, S"},
        {"Blade Ward","Abjuration","1 Action","Self","1 round","V, S"},
        {"Booming Blade","Evocation","1 Action","Self (5-foot radius)","1 round","S, M"},
        {"Chill Touch","Necromancy","1 Action","120 Feet","1 round","V, S"},
        {"Control Flames","Transmutation","1 Action","60 Feet","Instantaneous or 1 hour","S"},
        {"Create Bonfire","Conjuration","1 Action","60 Feet","Concentration up to 1 minute","V, S"},
        {"Dancing Lights","Evocation","1 Action","120 Feet","Concentration up to 1 minute","V, S, M"},
        {"Decompose (HB)","Necromancy HB","1 Action","Touch","1 minute","V, S"},
        {"Druidcraft","Transmutation","1 Action","30 Feet","Instantaneous","V, S"},
        {"Eldritch Blast","Evocation","1 Action","120 Feet","Instantaneous","V, S"},
        {"Encode Thoughts","Enchantment","1 Action","Self","8 hours","S"},
        {"Fire Bolt","Evocation","1 Action","120 Feet","Instantaneous","V, S"},
        {"Friends","Enchantment","1 Action","Self","Concentration up to 1 minute","S, M"},
        {"Frostbite","Evocation","1 Action","60 Feet","Instantaneous","V, S"},
        {"Green-Flame Blade","Evocation","1 Action","Self (5-foot radius)","Instantaneous","S, M"},
        {"Guidance","Divination","1 Action","Touch","Concentration up to 1 minute","V, S"},
        {"Gust","Transmutation","1 Action","30 Feet","Instantaneous","V, S"},
        {"Hand of Radiance (UA)","Evocation","1 Action","5 feet","Instantaneous","V, S"},
        {"Infestation","Conjuration","1 Action","30 Feet","Instantaneous","V, S, M"},
        {"Light","Evocation","1 Action","Touch","1 hour","V, M"},
        {"Lightning Lure","Evocation","1 Action","Self (15-foot radius)","Instantaneous","V"},
        {"Mage Hand","Conjuration","1 Action","30 Feet","1 minute","V, S"},
        {"Magic Stone","Transmutation","1 Bonus Action","Touch","1 minute","V, S"},
        {"Mending","Transmutation","1 Minute","Touch","Instantaneous","V, S, M"},
        {"Message","Transmutation","1 Action","120 feet","1 round","V, S, M"},
        {"Mind Sliver","Enchantment","1 Action","60 feet","1 round","V"},
        {"Minor Illusion","Illusion","1 Action","30 feet","1 minute","S, M"},
        {"Mold Earth","Transmutation","1 Action","30 feet","Instantaneous or 1 hour","S"},
        {"On/Off","Transmutation T","1 Action","60 feet","Instantaneous","V, S"},
        {"Poison Spray","Conjuration","1 Action","10 feet","Instantaneous","V, S"},
        {"Prestidigitation","Transmutation","1 Action","10 feet","Up to 1 hour","V, S"},
        {"Primal Savagery","Transmutation","1 Action","Self","Self","S"},
        {"Produce Flame","Conjuration","1 Action","Self","10 minutes","V, S"},
        {"Ray of Frost","Evocation","1 Action","60 feet","Instantaneous","V, S"},
        {"Resistance","Abjuration","1 Action","Touch","Concentration up to 1 minute","V, S, M"},
        {"Sacred Flame","Evocation","1 Action","60 feet","Instantaneous","V, S"},
        {"Sapping Sting","Necromancy D","1 Action","30 feet","Instantaneous","V, S"},
        {"Shape Water","Transmutation","1 Action","30 feet","Instantaneous or 1 hour","S"},
        {"Shillelagh","Transmutation","1 Bonus Action","Touch","1 minute","V, S, M"},
        {"Shocking Grasp","Evocation","1 Action","Touch","Instantaneous","V, S"},
        {"Spare the Dying","Necromancy","1 Action","Touch","Instantaneous","V, S"},
        {"Sword Burst","Conjuration","1 Action","Self (5-foot radius)","Instantaneous","V"},
        {"Thaumaturgy","Transmutation","1 Action","30 feet","Up to 1 minute","V"},
        {"Thorn Whip","Transmutation","1 Action","30 feet","Instantaneous","V, S, M"},
        {"Thunderclap","Evocation","1 Action","Self (5-foot radius)","Instantaneous","S"},
        {"Toll the Dead","Necromancy","1 Action","60 feet","Instantaneous","V, S"},
        {"True Strike","Divination","1 Action","30 feet","Concentration up to 1 round","S"},
        {"Vicious Mockery","Enchantment","1 Action","60 feet","Instantaneous","V"},
        {"Virtue (UA)","Abjuration","1 Action","Touch","1 round","V, S"},
        {"Word of Radiance","Evocation","1 Action","5 feet","Instantaneous","V, M"}};
        for (int i = 0; i < dummyData.length; i++) {
            String dummierData[] = dummyData[i];
                model.id = i;
                model.name = dummierData[0];
                model.school = dummierData[1];
                model.castingTime = dummierData[2];
                model.range = dummierData[3];
                model.durration = dummierData[4];
                model.components = dummierData[5];
                Database.set(model);
        }
    }

}