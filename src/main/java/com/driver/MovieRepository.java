package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    private Map<String, Movie> movieMap = new HashMap<>();
    private Map<String, Director> directorMap = new HashMap<>();
    private Map<String, List<String>> DirMoviesMap = new HashMap<>();


    public String addMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
        return "Movie Added Successfully";
    }

    public String addDirector(Director director) {
        directorMap.put(director.getName(), director);
        return "Director Added Successfully";
    }

    public String addMovieDirectorPair(String movie, String director) {
        List<String> list = DirMoviesMap.getOrDefault(director, new ArrayList<>());
        list.add(movie);
        DirMoviesMap.put(director, list);
        return "Successfully paired";
    }

    public Movie getMovieByName(String name) {
        if(movieMap.containsKey(name)){
            return movieMap.get(name);
        }
        else {
            return new Movie();
        }
    }

    public Director getDirectorByName(String name) {
        if(directorMap.containsKey(name)){
            return directorMap.get(name);
        }
        else{
            return new Director();
        }
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return DirMoviesMap.getOrDefault(directorName, new ArrayList<>());
    }

    public List<String> findAllMovies() {
        List<String> list = new ArrayList<>();
        for(String key : movieMap.keySet()){
            list.add(key);
        }
        return list;
    }

    public String deleteDirectorByName(String name) {
        directorMap.remove(name);

        List<String> DirectorsAllMovie = getMoviesByDirectorName(name);
        for(String mov : DirectorsAllMovie){
            movieMap.remove(mov);
        }

        DirMoviesMap.remove(name);

        return "Successfully Deleted";
    }

    public String deleteAllDirectors() {
        for(String dirName : directorMap.keySet()){
            deleteDirectorByName(dirName);
        }
        return "All director Deleted Successfully";
    }
}
