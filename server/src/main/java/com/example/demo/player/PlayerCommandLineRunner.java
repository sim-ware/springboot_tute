package com.example.demo.player;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PlayerCommandLineRunner implements CommandLineRunner {

    private final PlayerRepository repository;

    public PlayerCommandLineRunner(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Document doc = Jsoup.connect("https://www.bbc.co.uk/sport/football/world-cup/top-scorers").get();
        String title = doc.title();
        Elements hTags = doc.getElementsByClass("top-player-stats__name gel-double-pica");
        Elements gTags = doc.getElementsByClass("top-player-stats__goals-scored-number");
        Elements jTags = doc.getElementsByClass("top-player-stats__assists-number gel-double-pica");
        System.out.println("title is: " + title);
        System.out.println("TopScorers are: " + hTags);
        System.out.println("Goals are: " + gTags);
        System.out.println("Assists are: " + jTags);
        String[] topScorers = new String[5];
        String[] goals = new String[5];
        String[] assists = new String[5];
        //
        int index = 0;
        for (Element element : hTags) {
            topScorers[index] = element.ownText();
            index++;
        }
        for (String player : topScorers) {
            System.out.println(player);
        }
        //
        int index_two = 0;
        for (Element element : gTags) {
            goals[index_two] = element.ownText();
            index_two++;
        }
        for (String player : goals) {
            System.out.println(player);
        }
        //
        int index_three = 0;
        for (Element element : jTags) {
            assists[index_three] = element.ownText();
            index_three++;
        }
        for (String player : assists) {
            System.out.println(player);
        }


        int index_four = 0;
        for (String player : topScorers) {
            repository.save(new Player(player, goals[index_four], assists[index_four]));
            System.out.println(player);
            System.out.println(goals[index_four]);
            System.out.println(assists[index_four]);
            index_four++;
        }
        repository.findAll().forEach(System.out::println);
    }

}