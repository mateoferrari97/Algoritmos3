package modelo.game;


import modelo.multiplicators.Multiplicator;
import modelo.options.*;
import modelo.questions.*;
import modelo.scorers.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({Player.class, BooleanScorer.class,BooleanQuestion.class,ArrayList.class})


 public class RoundTest {
    private Game gameMock = spy(new Game());
    private Player playerMock = spy(new Player());
    private List<Option> optionsMock = spy(new ArrayList<>());
    private BooleanScorer scorerMock= spy(new BooleanScorer());
    private Question questionMock = spy(new BooleanQuestion( "",optionsMock,scorerMock));

    public Round setUpRound(){
        Player[] players = new Player[]{playerMock,playerMock};
        return new Round(players,questionMock);
    }
   @Test
    public void testGetTurn(){
        doNothing().when(gameMock).setNextRound();
        Round round = setUpRound();
        Turn turn = round.getTurn(gameMock);

        Assert.assertNotEquals(turn,round.getTurn(gameMock));

    }

    @Test
    public void testGetQuestionFromARound(){
        when(questionMock.getText()).thenReturn("hola");
        Round round = setUpRound();

        Question question = round.getQuestion();
        Assert.assertEquals(question.getText(),"hola");

    }
    @Test
    public void testGetMultiplicators(){
        Round round = setUpRound();
        List<Multiplicator> multiplicatorsMock = spy(new ArrayList<Multiplicator>());
        doReturn(multiplicatorsMock).when(questionMock).getMultiplicators();

        List<Multiplicator> multiplicators =round.getMultiplicator();
        Assert.assertEquals(multiplicators,multiplicatorsMock);
    }

}