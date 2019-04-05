
public class Question  {

    private String question;
    private String response1;
    private String response2;
    private String response3;
    private String response4;
    private String answer;

    public Question(String question, String response1, String response2, String response3, String response4, String answer) {
        this.question = question;
        this.response1 = response1;
        this.response2 = response2;
        this.response3 = response3;
        this.response4 = response4;
        this.answer = answer;
    }

    public String getQuestion() {return this.question;}
    public void setQuestion(String question) {this.question = question;}
    public String getResponse1() {return this.response1;}
    public void setResponse1(String response1) {this.response1 = response1;}
    public String getResponse2() {return this.response2;}
    public void setResponse2(String response2) {this.response2 = response2;}
    public String getResponse3() {return this.response3;}
    public void setResponse3(String response3) {this.response3 = response3;}
    public String getResponse4() {return this.response4;}
    public void setResponse4(String response4) {this.response4 = response4;}
    public String getAnswer() {return this.answer;}
    public void setAnswer(String answer) {this.answer = answer;}
}