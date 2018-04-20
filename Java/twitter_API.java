package teste_bigdata_twitter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twitter_API {

	public static Long idTweet = null;
	public static String name = null, screenName = null, lang = null, textTweet = null, createdAt = null;
	public static int followers;

	static int count = 100;

	static String CONSUMER_KEY = "*************************";
	static String CONSUMER_KEY_SECRET = "**************************************************";
	static String AccessToken = "**************************************************";
	static String AccessTokenSecret = "*********************************************";

	static String[] hashtags = { "#brasil", "#brazil", "#brasil2017", "#brazil2017", "#carnaval", "#tourism", "#bahia",
			"#riodejaneiro", "#saopaulo" };
	
	static String nomeArquivo = "tweets";

	public static void capturaTweets() throws Exception {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setTweetModeExtended(true);

		cb.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
				.setOAuthAccessToken(AccessToken).setOAuthAccessTokenSecret(AccessTokenSecret);

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		for (String s : hashtags) {

			Query query = new Query(s);
			query.setCount(count);
			QueryResult result = twitter.search(query);

			for (Status tweet : result.getTweets()) {

				idTweet = tweet.getId();
				// System.out.println(name);
								
				name = tweet.getUser().getName().replaceAll("\t", " ");
				// System.out.println(name);

				screenName = tweet.getUser().getScreenName();
				// System.out.println(screenName);

				followers = (Integer) tweet.getUser().getFollowersCount();
				// System.out.println(followers);

				lang = tweet.getLang();
				// System.out.println(lang);

				createdAt = tweet.getCreatedAt().toString().substring(4, 19);
				// System.out.println(createdAt);

				textTweet = tweet.getText();
				textTweet = textTweet.replaceAll("\n", " ").replaceAll("\t", " ");
				// System.out.println(textTweet);

				String texto = s + "\t" +  idTweet + "\t" + name + "\t" + screenName + "\t" + followers + "\t" + lang + "\t" + createdAt
						+ "\t" + textTweet;

				System.out.println(texto);

				gravaArquivo(nomeArquivo, texto, true);

			}
		}
		readTxtUsingLoad(nomeArquivo);

	}

	public static void gravaArquivo(String nomeArquivo, String conteudo, boolean append) throws IOException {
		
		File arquivo = new File(nomeArquivo + ".txt");
		FileWriter grava = new FileWriter(arquivo, append);
		PrintWriter escreve = new PrintWriter(grava);
		escreve.println(conteudo);
		escreve.close();
		grava.close();

	}

	private static void readTxtUsingLoad(String nomeArquivo) throws Exception {
		
		Connection conn = Conexao.abrir();
		
		String loadQuery = "LOAD DATA LOCAL INFILE '" + nomeArquivo + ".txt" + "' INTO TABLE tweets FIELDS TERMINATED BY '\t'"
				+ " LINES TERMINATED BY '\r\n' (hashtag, idTweet, name, ScreenName, followers, lang, createdAt, textTweet) ";
		System.out.println(loadQuery);
		
		Statement stmt = conn.createStatement();
		stmt.execute(loadQuery);
		conn.close();

	}

	public static void main(String[] args) throws Exception {

		capturaTweets();


	}

}
