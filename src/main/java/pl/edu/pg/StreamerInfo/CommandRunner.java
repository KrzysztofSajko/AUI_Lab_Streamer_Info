package pl.edu.pg.StreamerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import java.io.Console;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {
    private GenreService genreService;
    private GameService gameService;
    private StreamerService streamerService;

    @Autowired
    public CommandRunner(GenreService genreService,
                         GameService gameService,
                         StreamerService streamerService){

        this.genreService = genreService;
        this.gameService = gameService;
        this.streamerService = streamerService;
    }
    @Override
    public void run(String... args) throws Exception {
        var scanner = new Scanner(System.in);

        String cmd = "stop";
        do {
            System.out.println("Enter command:");
            if (scanner.hasNext()){
                cmd = scanner.nextLine();
            }
            switch (cmd){
                case "help":
                    System.out.println("help - this help");
                    System.out.println("list-categories - list of all categories");
                    System.out.println("list-elements - list of all items");
                    System.out.println("add - add item");
                    System.out.println("delete - delete item");
                    System.out.println("exit - exit program");
                    break;
                case "list-categories":
                    System.out.println("- Streamer");
                    System.out.println("- Game");
                    System.out.println("- Genre");
                    break;
                case "list-elements":
                    System.out.println("Streamers:");
                    streamerService.findAll().forEach(streamer -> System.out.println("- " + streamer));
                    System.out.println("Games:");
                    gameService.findAll().forEach(game -> System.out.println("- " + game));
                    System.out.println("Genres:");
                    genreService.findAll().forEach(genre -> System.out.println("- " + genre));
                    break;
                case "add":
                    System.out.println("Choose category [Streamer / Game / Genre]:");
                    if (scanner.hasNext()){
                        var category = scanner.nextLine();
                        switch (category){
                            case "Streamer":
                                System.out.println("Adding streamers is not implemented yet.");
                                break;
                            case "Game":
                                System.out.println("Adding games is not implemented yet.");
//                                System.out.println("Name:");
//                                if (scanner.hasNext()) {
//                                    var name = scanner.nextLine();
//                                    System.out.println("Abbreviation:");
//                                    if (scanner.hasNext()){
//                                        var abb = scanner.nextLine();
//                                        System.out.println("Genre name:");
//                                        if (scanner.hasNext()){
//                                            var genreName = scanner.nextLine();
//                                            genreService.find(genreName).ifPresentOrElse();
//                                        }
//                                    }
//                                }
                                break;
                            case "Genre":
                                System.out.println("Name:");
                                if (scanner.hasNext()) {
                                    var name = scanner.nextLine();
                                    System.out.println("Description:");
                                    if (scanner.hasNext()){
                                        var description = scanner.nextLine();
                                        var genre = Genre.builder()
                                                .name(name)
                                                .description(description)
                                                .build();
                                        genreService.add(genre);
                                    }
                                }
                                break;
                            default:
                                System.out.println("There is no such category: " + category);
                        }
                    }
                    break;
                case "delete":
                    System.out.println("Deleting is not implemented yet.");
                    break;
                case "exit":
                    break;
                case "streamer-by-genre":
                    System.out.println("Enter genre name:");
                    if (scanner.hasNext()){
                        var genreName = scanner.nextLine();
                        streamerService.findAllByGenre(genreName).forEach(streamer -> System.out.println("- " + streamer));
                    }
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }while (!cmd.equals("exit"));
        System.out.println("Exiting...");
//        genreService.findAll().forEach(System.out::println);
    }
}
