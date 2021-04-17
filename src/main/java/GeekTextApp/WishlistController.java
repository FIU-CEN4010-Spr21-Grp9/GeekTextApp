package GeekTextApp;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WishlistController {
	String starterURL;
	public WishlistController()
	{
		starterURL = GeekTextApp.ROOT_URL;


	}

	public void newList (int userID, String name) {
		String fullURL;
		RestTemplate restTemplate = new RestTemplate();
		List<Wishlist> wishlist;
		fullURL = starterURL + "/wishlist/newlist?userID={id}&name={name}";

		ResponseEntity<List<Wishlist>> responseEntity = restTemplate.exchange(
				fullURL,
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<List<Wishlist>>() {},
				userID,name);
		wishlist = responseEntity.getBody();
	}

	public void insert(int userID, int bookID, int wishlistID)
	{
		String fullURL;
		RestTemplate restTemplate = new RestTemplate();
		List<Wishlist> wishlist;
		fullURL = starterURL + "/wishlist/insert?userID={id}&bookID={id}&wishlistID={id}";
		
		ResponseEntity<List<Wishlist>> responseEntity = restTemplate.exchange(
				fullURL,
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<List<Wishlist>>() {},
				userID,bookID,wishlistID);
		wishlist = responseEntity.getBody();
	}

	public void move (int userID, int wishlistItemID, int wishlistID) {
		String fullURL;
		RestTemplate restTemplate = new RestTemplate();
		List<Wishlist> wishlist;
		fullURL = starterURL + "/wishlist/move?userID={id}&wishlistItemID={id}&wishlistID={id}";

		ResponseEntity<List<Wishlist>> responseEntity = restTemplate.exchange(
				fullURL,
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<List<Wishlist>>() {},
				userID,wishlistItemID,wishlistID);
		wishlist = responseEntity.getBody();
	}

	public void delete (int userID, int wishlistItemID) {
		String fullURL;
		RestTemplate restTemplate = new RestTemplate();
		List<Wishlist> wishlist;
		fullURL = starterURL + "/wishlist/delete?userID={id}&wishlistItemID={id}";

		ResponseEntity<List<Wishlist>> responseEntity = restTemplate.exchange(
				fullURL,
				HttpMethod.DELETE,
				null,
				new ParameterizedTypeReference<List<Wishlist>>() {},
				userID,wishlistItemID);
		wishlist = responseEntity.getBody();
	}

	public void deleteList (int userID, int wishlistID) {
		String fullURL;
		RestTemplate restTemplate = new RestTemplate();
		List<Wishlist> wishlist;
		fullURL = starterURL + "/wishlist/deleteList?userID={id}&wishlistID={id}";

		ResponseEntity<List<Wishlist>> responseEntity = restTemplate.exchange(
				fullURL,
				HttpMethod.DELETE,
				null,
				new ParameterizedTypeReference<List<Wishlist>>() {},
				userID,wishlistID);
		wishlist = responseEntity.getBody();
	}

	public List<Wishlist> list (int userID, int wishlistID) {
		String fullURL;
		RestTemplate restTemplate = new RestTemplate();
		List<Wishlist> wishlist;
		fullURL = starterURL + "/wishlist/list?userID={id}&wishlistID={id}";

		ResponseEntity<List<Wishlist>> responseEntity = restTemplate.exchange(
				fullURL,
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<List<Wishlist>>() {},
				userID,wishlistID);
		wishlist = responseEntity.getBody();
		return wishlist;
	}

	public List<WishlistName> allLists (int userID) {
		String fullURL;
		RestTemplate restTemplate = new RestTemplate();
		List<WishlistName> wishlist;
		fullURL = starterURL + "/wishlist/allLists?userID={id}";

		ResponseEntity<List<WishlistName>> responseEntity = restTemplate.exchange(
				fullURL,
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<List<WishlistName>>() {},
				userID);
		wishlist = responseEntity.getBody();
		return wishlist;
	}
}