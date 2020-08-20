package step.by.step;

public class LoginData {
    private final String robotester;
    private final String electrostalin;

    public LoginData(String robotester, String electrostalin) {
        this.robotester = robotester;
        this.electrostalin = electrostalin;
    }

    public String getRobotester() {
        return robotester;
    }

    public String getElectrostalin() {
        return electrostalin;
    }
}
