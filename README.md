# ProjectFlixster
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).


- User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
- Views are responsive for both landscape/portrait mode.
   - In portrait mode, the poster image, title, and movie overview is shown.
   - In landscape mode, the rotated alternate layout uses the backdrop image instead and show the title and movie overview to the right of it.
- Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- Allow video posts to be played in full-screen using the YouTubePlayerView.

### App Walkthough GIF

<img src="https://github.com/The-Yigit/ProjectFlixster/blob/master/flixsterGIF1.gif" width=250><br>
<img src="https://github.com/The-Yigit/ProjectFlixster/blob/master/GIF2.gif" width=550><br>

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
