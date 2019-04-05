
public class Stats {

    private int score;
    private int attack;
    private int defence;

    public Stats(int attack, int defence) {
        this.score = 0;
        this.attack = attack;
        this.defence = defence;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getScore() {
        return score;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addStats(String name) {
        if (name.equals("katana")) {
            increaseAttack(40);
        } else if (name.equals("sabre")) {
            increaseAttack(15);
        } else if (name.equals("potion")) {
            increaseDefence(40);
        }
    }

    public void increaseAttack(int attack) {
        if (attack > 0)
            this.attack += attack;
    }

    public void increaseDefence(int defence) {
        if (defence > 0)
            this.defence += defence;
    }

    public void decreaseAttack(int attack) {
        if (attack < 0)
            this.attack -= attack;
        if (this.attack < 0)
            this.setAttack(0);
    }

    public void decreaseDefence(int defence) {
        if (defence < 0)
            this.defence -= defence;
        if (this.defence < 0)
            this.setDefence(0);
    }
}