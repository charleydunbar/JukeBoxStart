package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import model.Jukebox;
import model.JukeboxAccount;
import model.JukeboxAccountCollection;
import model.LimitCounter;
import model.Song;
import model.TheDecider;

public class CharleyTest {

	@Test
	public void testSong() {
		Song song = new Song("flute.aif", 6);

		assertTrue("flute.aif".equals(song.getName()));
		assertEquals(6, song.getSeconds());

		assertTrue(song.playable());

		assertTrue(song.play());
		assertTrue(song.playable());

		assertTrue(song.play());
		assertTrue(song.playable());

		assertTrue(song.play());
		assertFalse(song.playable());

		assertFalse(song.play());
	}

	@Test
	public void testJukeboxAccount() {
		String name = "Dave";
		char[] pass = { 'y', 'a', 'y' };
		JukeboxAccount acc = new JukeboxAccount(name, pass);

		assertTrue("Dave".equals(acc.getID()));
		assertEquals(90000, acc.getSeconds());

		char[] tmp = acc.getPassword();

		assertTrue('y' == tmp[0]);
		assertTrue('a' == tmp[1]);
		assertTrue('y' == tmp[2]);

		assertTrue(acc.playable());

		assertTrue(acc.play(6));
		assertEquals(89994, acc.getSeconds());
		assertTrue(acc.playable());

		assertTrue(acc.play(6));
		assertEquals(89988, acc.getSeconds());
		assertTrue(acc.playable());

		assertTrue(acc.play(6));
		assertEquals(89982, acc.getSeconds());
		assertFalse(acc.playable());

		assertFalse(acc.play(6));
		assertEquals(89982, acc.getSeconds());
	}

	@Test
	public void testTheDecider() {
		TheDecider theDecider = new TheDecider();

		String name = "Dave";
		char[] pass = { 'y', 'a', 'y' };
		JukeboxAccount acc = new JukeboxAccount(name, pass);
		JukeboxAccount acc2 = new JukeboxAccount(name, pass);

		Song song = new Song("flute.aif", 6);
		Song song2 = new Song("flute.aif", 6);

		assertTrue(theDecider.decide(acc, song));

		song.play();
		assertTrue(theDecider.decide(acc, song));

		acc.play(6);
		assertTrue(theDecider.decide(acc, song));

		song.play();
		assertTrue(theDecider.decide(acc, song));

		acc.play(6);
		assertTrue(theDecider.decide(acc, song));

		song.play();
		assertFalse(theDecider.decide(acc, song));

		assertTrue(theDecider.decide(acc, song2));
		acc.play(6);
		assertFalse(theDecider.decide(acc, song2));

		assertTrue(theDecider.decide(acc2, song2));

		acc2.play(90000);
		assertFalse(theDecider.decide(acc2, song2));
	}

	@Test
	public void testLimitCounter() {
		LimitCounter limit = new LimitCounter(3);

		assertTrue(limit.playable(LocalDate.now()));
		assertTrue(limit.performNew(LocalDate.now()));

		assertTrue(limit.playable(LocalDate.now()));
		assertTrue(limit.performNew(LocalDate.now()));

		assertTrue(limit.playable(LocalDate.now()));
		assertTrue(limit.performNew(LocalDate.now()));

		assertFalse(limit.playable(LocalDate.now()));
		assertFalse(limit.performNew(LocalDate.now()));

		assertTrue(limit.playable(LocalDate.now().plusDays(1)));
		assertTrue(limit.performNew(LocalDate.now().plusDays(1)));
	}

	@Test
	public void testJukeboxAccountCollection() {
		JukeboxAccountCollection accs = new JukeboxAccountCollection();

		assertTrue(accs.getAccount("Dave", null) == null);

		String name = "Dave";
		char[] pass = { 'y', 'a', 'y' };
		char[] wrong = { 'y', 'a', 'a' };
		char[] wrong2 = { 'y', 'a' };
		char[] wrong3 = { 'y', 'a', 'y', 'a' };
		accs.add(name, "yay");

		assertTrue(accs.getAccount("Dave", wrong) == null);
		assertTrue(accs.getAccount("Dave", wrong2) == null);
		assertTrue(accs.getAccount("Dave", wrong3) == null);
		assertTrue(accs.getAccount("Dave", null) == null);

		JukeboxAccount acc = accs.getAccount(name, pass);

		assertTrue("Dave".equals(acc.getID()));
		assertEquals(90000, acc.getSeconds());

		char[] tmp = acc.getPassword();

		assertTrue('y' == tmp[0]);
		assertTrue('a' == tmp[1]);
		assertTrue('y' == tmp[2]);

		assertTrue(acc.playable());

		assertTrue(acc.play(6));
		assertEquals(89994, acc.getSeconds());
		assertTrue(acc.playable());

		assertTrue(acc.play(6));
		assertEquals(89988, acc.getSeconds());
		assertTrue(acc.playable());

		assertTrue(acc.play(6));
		assertEquals(89982, acc.getSeconds());
		assertFalse(acc.playable());

		assertFalse(acc.play(6));
		assertEquals(89982, acc.getSeconds());
	}

	@Test
	public void testJukebox() {
		Jukebox jukebox = new Jukebox();
		char[] pass = { '1' };
		char[] pass2 = { '2', '2' };

		assertFalse(jukebox.login("Chris", null));
		assertTrue(jukebox.getAccount() == null);
		assertFalse(jukebox.tryPlay("flute.aif"));

		assertTrue(jukebox.login("Chris", pass));
		assertTrue("Chris".equals(jukebox.getAccount().getID()));

		assertFalse(jukebox.tryPlay("hey"));
		assertTrue(jukebox.tryPlay("flute.aif"));

		assertEquals(89994, jukebox.getAccount().getSeconds());

		assertTrue(jukebox.tryPlay("spacemusic.au"));
		assertTrue(jukebox.tryPlay("flute.aif"));
		assertFalse(jukebox.tryPlay("spacemusic.au"));

		assertTrue(jukebox.login("Devon", pass2));

		assertTrue(jukebox.tryPlay("spacemusic.au"));
		assertTrue(jukebox.tryPlay("spacemusic.au"));
		assertFalse(jukebox.tryPlay("spacemusic.au"));
		assertTrue(jukebox.tryPlay("flute.aif"));
		assertFalse(jukebox.tryPlay("flute.aif"));
	}
}
