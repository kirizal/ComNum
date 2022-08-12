package ProgramFunctionality;

public enum ProgramState {
    ProcessingInput(0),
    ReportingErrors(1);


    private int state;
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }


    ProgramState(int state){
        this.state = state;
    }
}
