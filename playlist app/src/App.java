import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;

public class App{

    public static class Playlist{
        private String name;
        private String info;
        private String rate;
        private List<Video> songs;
        public Playlist(String name, String info, String rate, List<Video> songs){
            this.name = name;
            this.info = info;
            this.rate = rate;
            this.songs = songs;
        }
    }
    public static class Video{
        private String title;
        private String link;
        public Video(String title, String link){
            this.title = title;
            this.link = link;
        }
    }


    public static int choice(String prompt, int min, int max){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print(prompt);
            String choose = in.nextLine();
            if (choose.matches("-?\\d+") && Integer.parseInt(choose) >= min && Integer.parseInt(choose) <= max){
                return Integer.parseInt(choose);
            }
            else{
                System.out.println("The option ' " + choose + " ' is incorrect. Please Re-Enter! ");
            }
            }
    }

    public static Video video_input(int i, Scanner in){
        System.out.print("Enter Video "+(i+1)+ " Title: ");
        String title = in.nextLine();
        System.out.print("Enter Video "+(i+1)+ " Link : ");
        String link = in.nextLine();
        Video video = new Video(title, link);
        return video;
    }

    public static List<Video> videos_input(){
        List<Video> videos = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int total = choice("Enter the amount of videos: ", 1, 1110);
        for (int i = 0; i < total; i+=1){
            Video video = video_input(i, in);
            videos.add(video);
        }
        return videos;
    }



    public static Playlist playlist_input(int i, Scanner in){
        System.out.print("Enter Playlist "+(i+1)+ " Name: ");
        String name = in.nextLine();
        System.out.print("Enter Playlist "+(i+1)+ " Info: ");
        String info = in.nextLine();
        System.out.print("Enter Playlist "+(i+1)+ " Rate: ");
        String rate = in.nextLine();
        List<Video> songs = videos_input();
        Playlist playlist = new Playlist(name, info, rate, songs);
        return playlist;


    }

    public static List<Playlist> playlists_input(){
        List<Playlist> playlists = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int total = choice("Enter the amount of playlists: ", 1, 1110);
        for (int i = 0; i < total; i+=1){
            Playlist playlist = playlist_input(i, in);
            playlists.add(playlist);
        }

        return playlists;
    }

    public static void write_videos(List<Video> list, int i){
        try(BufferedWriter f = new BufferedWriter(new FileWriter("video"+i+".txt"))){
            int total = list.size();
            f.write(total);
            f.newLine();
            for (int j = 0; j < total; j+=1){
                f.write(list.get(j).title);
                f.newLine();
                f.write(list.get(j).link);
                f.newLine();
            }


        }catch(IOException e){
            System.err.println(e);
        }


    }

    public static void write_playlists(List<Playlist> list){
        try(BufferedWriter f = new BufferedWriter(new FileWriter("playlists.txt"))){
            int total = list.size();
            f.write(total);
            f.newLine();
            for (int i = 0 ; i < total; i+=1){
                f.write(list.get(i).name);
                f.newLine();
                f.write(list.get(i).info);
                f.newLine();
                f.write(list.get(i).rate);
                f.newLine();
                f.write(list.get(i).songs.size());
                f.newLine();
                write_videos(list.get(i).songs, i);
            }
            int j = total;
            while (true) {
            File file = new File("video" + j + ".txt");
            if (file.delete()) {
                System.out.println("File deleted successfully: " + file.getName());
                j++;
            } else {
                break;
            }
            }

        }catch(IOException e){
            System.err.println("Error 1");
        }
    }

    public static List<Video> read_videos(int i){
        List<Video> videos = new ArrayList<>();
        try (BufferedReader f = new BufferedReader(new FileReader("video"+i+".txt"))) {
            int total = f.read();
            f.readLine();
            for(int j = 0; j < total; j+=1){
                String title = f.readLine();
                String link = f.readLine();
                Video video = new Video(title, link);
                videos.add(video);
            }
            
        } catch (Exception e) {
        }
        return videos;

    }

    public static List<Playlist> read_playlists(){
        List<Playlist> playlists = new ArrayList<>();
        try(BufferedReader f = new BufferedReader(new FileReader("playlists.txt"))){
            int total = f.read();
            f.readLine();
            for (int i = 0; i<total; i+=1){
                String name = f.readLine();
                String info = f.readLine();
                String rate = f.readLine();
                int skip = f.read();
                f.readLine();
                List<Video> songs = read_videos(i);
                Playlist playlist = new Playlist(name, info, rate, songs);
                playlists.add(playlist);


            }


        }catch(IOException e){
            System.err.println(e);
        }
        return playlists;
    }
    public static void print_videos(List<Video> list){
        int total = list.size();
        for (int i = 0; i < total; i+=1){
            System.out.println("         " +  " "  + " |__Video " + (i+1) + " Title : " + list.get(i).title);
            System.out.println("         " +  " "  + " |__Video " + (i+1) + " Link  : " + list.get(i).link);
        }
    }

    public static void print_playlists(List<Playlist> list){
        int total = list.size();
        for (int i = 0; i < total; i+=1){
            System.out.println("-------------");
            System.out.println("Playlist " + (i+1) + " name   : " + list.get(i).name);
            System.out.println("Playlist " + (i+1) + " info   : " + list.get(i).info);
            System.out.println("Playlist " + (i+1) + " rate   : " + list.get(i).rate);
            System.out.println("Playlist " + (i+1) + " videos : " + list.get(i).songs.size());
            print_videos(list.get(i).songs);

        }

    }

    public static List<Playlist> menu_1(){
        List<Playlist> list = playlists_input();
        return list;
    }

    public static void menu_4(){
        List<Playlist> playlist = read_playlists();
        print_playlists(playlist);
    }

    public static void ask_yn(List<Playlist> list, Scanner in){
        System.out.print("Do you want to save your Playlists? (Y/N) :  ");
                String opt = in.nextLine();
                if (opt.equals("Y") || opt.equals("y") || opt.equals("yes") || opt.equals("Yes")){
                    write_playlists(list);
                    System.out.println("Saved Successfully!!!");

                }else if (opt.equals("n") || opt.equals("no") || opt.equals("No") || opt.equals("N")){
                    System.out.println("Back to menu!! ");
                    show_menu();
                }else{
                    System.out.println("The option ' " + opt + " ' is incorrect. Please Re-Enter! ");
                    ask_yn(list, in);
                }

    }

    public static void edit_videos(int i){
        Scanner in = new Scanner(System.in);
        List<Playlist> playlist = read_playlists();
        List<Video> list = playlist.get(i).songs;
        print_videos(playlist.get(i).songs);
        int choice1 = choice("Choose video you want to edit (1-"+list.size()+"): ", 1, list.size());
        System.out.println("Video Edit: ");
        System.out.println("1. Title");
        System.out.println("2. Link");
        int choice2 = choice("Option (1-2):  ",1, 2);
        if (choice2 == 1){
            System.out.print("Enter new video name:  ");
            String new_title = in.nextLine();
            list.get(choice1-1).title = new_title;
            write_playlists(playlist);
        }else if (choice2 == 2){
            System.out.print("Enter new video link:  ");
            String new_link = in.nextLine();
            list.get(choice1-1).link = new_link;
            write_playlists(playlist);
        }else{
            System.out.println("Error");
            edit_videos(i);
        }

        
        
    }

    public static void edit_playlists(){
        Scanner in = new Scanner(System.in);
        List<Playlist> playlist = read_playlists();
        int choice1 = choice("Enter the playlist you want to edit (1-"+playlist.size()+"):  ", 1, playlist.size());
        System.out.println("Playlist Edit: ");
        System.out.println("1. Name");
        System.out.println("2. Info");
        System.out.println("3. Rate");
        System.out.println("4. Videos");
        int choice2 = choice("Enter your option (1-4): ", 1, 4);
        if (choice2 == 1){
            System.out.print("Change playlist name: ");
            String new_name = in.nextLine();
            playlist.get(choice1-1).name = new_name;
            write_playlists(playlist);


        }else if (choice2 == 2){
            System.out.print("Change playlist info: ");
            String new_info = in.nextLine();
            playlist.get(choice1-1).info = new_info;
            write_playlists(playlist);
        }else if (choice2 == 3){
            System.out.print("Change playlist rate: ");
            String new_rate = in.nextLine();
            playlist.get(choice1-1).rate = new_rate;
            write_playlists(playlist);
        }else if (choice2 == 4){
            edit_videos(choice1-1);
        }else{
            System.out.println("Incorrect option, pls re-enter");
            edit_playlists();
        }
    }

    public static void menu_2(){
        List<Playlist> playlist = read_playlists();
        print_playlists(playlist);
        System.out.println("Option 1: Edit Playlist");
        System.out.println("Option 2: Add  Playlist");
        System.out.println("Option 3: Del  Playlist");
        int choose = choice("Enter your option (1-3):  ", 1, 3);
        if (choose == 1){
            edit_playlists();
        }else if (choose == 2){
            
        }

    }


    public static void delete_playlist() {
    Scanner in = new Scanner(System.in);
    
    List<Playlist> playlists = read_playlists();
    
    if (playlists.size() == 0) {
        System.out.println("No playlists available to delete.");
        System.out.println("Press Enter to continue...");
        in.nextLine();
        return;
    }
    
    print_playlists(playlists);
    
    int plNumber = choice("Enter the playlist number you want to delete (1-" + playlists.size() + "): ", 1, playlists.size());
    
    String playlistName = playlists.get(plNumber - 1).name;
    
    System.out.print("Are you sure you want to delete \"" + playlistName + "\"? (Y/N): ");
    String confirm = in.nextLine().trim();
    
    if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("yes")) {
        playlists.remove(plNumber - 1);
        write_playlists(playlists);
        System.out.println("Playlist \"" + playlistName + "\" has been deleted successfully.");
        System.out.println("Data has been updated and saved.");
    } else {
        System.out.println("Delete operation cancelled.");
    }
    
    System.out.println("\nPress Enter to return to menu...");
    in.nextLine();
}


    public static void menu_6() {
    Scanner in = new Scanner(System.in);
    System.out.println("All changes are automatically saved after each operation.");
    System.out.println("Do you want to force save current data again? (Y/N): ");
    String opt = in.nextLine().trim();
    
    if (opt.equalsIgnoreCase("Y") || opt.equalsIgnoreCase("yes")) {
        List<Playlist> current = read_playlists();
        write_playlists(current);
        System.out.println("Data has been saved successfully!!!");
    } else {
        System.out.println("No additional save performed.");
    }
    
    System.out.println("\nPress Enter to return to menu...");
    in.nextLine();
}


public static void menu_7() {
    Scanner in = new Scanner(System.in);
    
    System.out.println("Are you sure you want to save all data and exit? (Y/N): ");
    String confirm = in.nextLine().trim();
    
    if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("yes")) {
        List<Playlist> current = read_playlists();
        write_playlists(current);
        
        System.out.println("Data saved successfully.");
        System.out.println("Thank you for using the program!");
        System.out.println("Goodbye!");
        
        System.exit(0);
    } else {
        System.out.println("Exit cancelled. Returning to menu...");
        System.out.println("Press Enter to continue...");
        in.nextLine();
    }
}


public static void menu_5(){
    List<Playlist> playlists = read_playlists();
    
    if (playlists.isEmpty()) {
        System.out.println("No playlists available to play videos.");
        System.out.println("Press Enter to return to menu...");
        new Scanner(System.in).nextLine();
        return;
    }
    
    print_playlists(playlists);
    
    int plChoice = choice("Select Playlist to play video from (1-" + playlists.size() + "): ", 1, playlists.size());
    List<Video> videos = playlists.get(plChoice-1).songs;
    
    if (videos.isEmpty()) {
        System.out.println("This playlist has no videos to play.");
        System.out.println("Press Enter to return to menu...");
        new Scanner(System.in).nextLine();
        return;
    }
    
    print_videos(videos);
    
    int vidChoice = choice("Select Video to play (1-" + videos.size() + "): ", 1, videos.size());
    
    String link = videos.get(vidChoice-1).link;
    String title = videos.get(vidChoice-1).title;
    
    System.out.println("Opening video: " + title);
    
    try {
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(new URI(link));
            System.out.println("Video is now playing in your default web browser...");
            System.out.println("Link: " + link);
        } else {
            System.out.println("Browser opening not supported on this system.");
            System.out.println("Please open this link manually: " + link);
        }
    } catch (Exception e) {
        System.out.println("Cannot open video automatically (error: " + e.getMessage() + ").");
        System.out.println("Please copy and paste this link into your browser:");
        System.out.println(link);
    }
    
    System.out.println("\nPress Enter to return to menu...");
    new Scanner(System.in).nextLine();
}

    public static void menu(){
        show_menu();
        while(true){
            System.out.println("Press '0' to show menu! ");
            int choose = choice("Enter your option (1-7) :  ", 0, 7);
            if (choose == 1){
                Scanner in = new Scanner(System.in);
                List<Playlist> list = menu_1();
                ask_yn(list, in);

            }else if (choose == 2){
                menu_2();

            }else if (choose == 3){
                delete_playlist();
            }else if (choose == 4){
                menu_4();
            }else if (choose == 5){
                menu_5();
            }else if (choose == 6){
                menu_6();
            }else if (choose == 7){
                menu_7();
            }else{
                show_menu();
            }

        }
    }

    public static void show_menu(){
        System.out.println("Option 1: Create Playlist");
        System.out.println("Option 2: Edit Playlist");
        System.out.println("Option 3: Delete Playlist");
        System.out.println("Option 4: Show Playlist");
        System.out.println("Option 5: Play Video");
        System.out.println("Option 6: Save");
        System.out.println("Option 7: Save & Exit");
    }

    public static void main(String[] args) {
        // List<Playlist> list = playlists_input();
        // write_playlists(list);
        // List<Playlist> playlist = read_playlists();
        // print_playlists(playlist);
        menu();

    }
}
