package client.dataStorage;



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class CollectionView {

	static private Map<Long, String> movieView = new LinkedHashMap<>();
	
	public CollectionView(LinkedHashMap<Long, String> movieView) {
		setMovieView(movieView);
		
	}

	static public Map<Long, String> getMovieView() {
		return movieView;
	}

	static public void setMovieView(LinkedHashMap<Long, String> movieView) {
		CollectionView.movieView = movieView;
	}

	
}
