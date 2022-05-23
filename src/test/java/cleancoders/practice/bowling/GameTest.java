package cleancoders.practice.bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void canRoll() {
        game.roll(0);
    }

    @Test
    public void gutterGame() {
        rollMany(0,20);
        assertThat(game.getScore()).isEqualTo(0);
    }

    @Test
    public void allOnes() {
        rollMany(1,20);
        assertThat(game.getScore()).isEqualTo(20);
    }

    @Test
    public void oneSpare() {
        rollSpare();
        game.roll(3);
        rollMany(17,0);
        assertThat(game.getScore()).isEqualTo(16);
    }

    @Test
    public void oneStrike() {
        rollStrike();
        game.roll(5);
        game.roll(3);
        rollMany(16, 0);
        assertThat(game.getScore()).isEqualTo(26);
    }

    @Test
    public void perfectGame() {
        rollMany(10,10);
        game.roll(10);
        game.roll(10);
        assertThat(game.getScore()).isEqualTo(300);
    }

    private void rollStrike() {
        game.roll(10);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollMany(int pins, int frames) {
        for (int i = 0; i < frames; i++) {
            game.roll(pins);
        }
    }


}
