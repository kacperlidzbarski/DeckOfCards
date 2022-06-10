import model.Cards;
import model.Deck;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class DrawCardsAndVerifyCorrectness {

    private static Boolean CardWasFound = false;



    @Test
    public  void shuffleAndDrawCards() {

//Obtain one deck and assert if the response body matches the schema from documentation. Also assert attribute values - check if deck_id is string, if success equals true, etc.
// (1st request from documentation with deck count set as 1)
//Save deck_id as it would be required for the next request


                //Send request to shuffle the cards
                Deck shuffleCardsResponse = given().
                                                    pathParam("deck_count",1).
                                                    baseUri("https://deckofcardsapi.com/api/deck/new/shuffle").
                                            when().
                                                    get("/?deck_count={deck_count}").
                                            then().
                                                    body(matchesJsonSchemaInClasspath("Schemas/DeckSchema.json")) //verify schema
                                                    .extract().body().as(Deck.class); // create an object from Body

                //Assert the fields form response
                Assert.assertEquals(shuffleCardsResponse.getDeck_id().getClass(),String.class); // Assert that Deck_id is a String
                Assert.assertTrue(shuffleCardsResponse.isSuccess()); //Assert that Success = true
                Assert.assertTrue(shuffleCardsResponse.isShuffled()); //Assert that Shuffled is true
                Assert.assertEquals( shuffleCardsResponse.getRemaining(),52,0);// Assert that remains 52 cards


                Object deckId= shuffleCardsResponse.getDeck_id(); // save deck_id as variable



                //Send request to get cards
                Cards cardsInHand = given().
                                            pathParam("count",52).pathParam("deck_id",deckId).
                                            baseUri("https://deckofcardsapi.com/api/deck").
                                    when().
                                            get("/{deck_id}/draw/?count={count}").
                                    then().
                                            extract().body().as(Cards.class);

                //Find a specific card in hand
                List <HashMap> ListOfCards = cardsInHand.getCards();
                for(HashMap specificCard : ListOfCards) {
                    if (specificCard.get("code").equals("0S")){
                        CardWasFound = true;
                    }
                }
                Assert.assertEquals(cardsInHand.getRemaining(),0,0); //Assert that you have no cards remaining
                Assert.assertTrue(CardWasFound);    //Assert that you hold a specific Card


    }
}
