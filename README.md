# Playlist Management Application

## Introduction
The `App` is a console-based Java application for managing video playlists. It allows users to create, edit, view, and persistently store playlists. Each playlist contains basic information (name, description, rating) and a list of videos (title and link).

Data is stored permanently in plain text files (`playlists.txt` and `videoN.txt` files), ensuring all information remains available after the program is restarted.

## Main Features
- **Create new playlists**: Enter any number of playlists (up to 1110 videos per playlist) with full details.
- **Edit playlists**: Modify playlist name, info, rating, or individual video titles and links.
- **Display playlists**: Print all playlists and their videos in a clear, formatted console output.
- **Persistent storage**: Automatically save data to files when requested. Automatically cleans up unused temporary files.
- **Load from files**: Automatically reads previously saved playlists when viewing or editing.
- **Interactive menu**: Complete menu system with options 1–7 (Create, Edit, Delete, Show, Play Video, Save, Save & Exit).

## System Requirements
- Java Development Kit (JDK) 8 or higher.
- No external libraries required (uses only standard Java packages).

## How to Compile and Run
1. Save the source code as `App.java`.
2. Open a terminal/command prompt in the same folder.
3. Compile the program:
   ```bash
   javac App.java


## You need to paste full of the web link (eg. 'https://www.google.com') (Not 'google.com')
