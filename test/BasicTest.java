import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	
	 @Before
	    public void setup() {
	        Fixtures.deleteAll();
	    }
	 
	@Test
	public void createAndRetrieveUser() {
	    // Create a new user and save it
	    new User("Dylan", "Bob", "password", "bob@gmail.com" ).save();
	    
	    // Retrieve the user with email address bob@gmail.com
	    User bob = User.find("byEmail", "bob@gmail.com").first();
	    
	    // Test 
	    assertNotNull(bob);
	    assertEquals("Bob", bob.prenom);
	    assertEquals("Dylan", bob.nom);
	    assertEquals("password", bob.motDePasse);
	    assertEquals("bob@gmail.com", bob.email);
	}
	
	@Test
	public void tryConnectAsUser() {
	    // Create a new user and save it
	    new User("Dylan", "Bob", "password", "bob@gmail.com" ).save();
	    
	    // Test 
	    assertNotNull(User.connect("bob@gmail.com", "password"));
	    assertNull(User.connect("bob@gmail.com", "badpassword"));
	    assertNull(User.connect("tom@gmail.com", "secret"));
	}
	
	@Test
	public void createListeDeCourse() {
	    // Create a new user and save it
	    User bob =  new User("Dylan", "Bob", "password", "bob@gmail.com" ).save();
	    
	    // Create a new post
	    new ListeDeCourse("Liste Vacances", bob).save();
	    
	    // Test that the post has been created
	    assertEquals(1, ListeDeCourse.count());
	    
	    // Retrieve all post created by bob
	    List<ListeDeCourse> bobListe = ListeDeCourse.find("byUser", bob).fetch();
	    
	    // Tests
	    assertEquals(1, bobListe.size());
	    ListeDeCourse firstListe = bobListe.get(0);
	    assertNotNull(firstListe);
	    assertEquals(bob, firstListe.user);
	    assertEquals("Liste Vacances", firstListe.nom);
	}
	
	@Test
	public void fullTest() {
	    Fixtures.load("initial-data.yml");
	 
	    // Count things
	    assertEquals(4, User.count());
	    assertEquals(29, Aliment.count());
	    assertEquals(4, Recette.count());
	    assertEquals(5, ListeDeCourse.count());
	    assertEquals(12, EtatFrigo.count());
	    
	    // Try to connect as users
	    assertNotNull(User.connect("emilie.paillous@gmail.com", "emilie"));
	    assertNotNull(User.connect("alice.millour@gmail.com", "alice"));
	    assertNotNull(User.connect("alexis.sciau@gmail.com", "alexis"));
	    assertNotNull(User.connect("mailys.reneaume@gmail.com", "mailys"));
	    assertNull(User.connect("emilie.paillous@gmail.com", "badpassword"));
	    assertNull(User.connect("tom@gmail.com", "secret"));
	 
	    // Trouve toutes les listes d'emilie
	    List<ListeDeCourse> listeEmilie = ListeDeCourse.find("user", "emilie").fetch();
	    assertEquals(1, listeEmilie.size());
	 
	    // Trouve toutes les listes de mailys
	    List<ListeDeCourse> listeMailys = ListeDeCourse.find("user", "emilie").fetch();
	    assertEquals(2, listeMailys.size());
	    
//	    // Find all comments related to bob's posts
//	    List<Comment> bobComments = Comment.find("post.author.email", "bob@gmail.com").fetch();
//	    assertEquals(3, bobComments.size());
//	 
//	    // Find the most recent post
//	    Post frontPost = Post.find("order by postedAt desc").first();
//	    assertNotNull(frontPost);
//	    assertEquals("About the model layer", frontPost.title);
//	 
//	    // Check that this post has two comments
//	    assertEquals(2, frontPost.comments.size());
//	 
//	    // Post a new comment
//	    frontPost.addComment("Jim", "Hello guys");
//	    assertEquals(3, frontPost.comments.size());
//	    assertEquals(4, Comment.count());
	}

}
