package main.java.package1;

public class AccounNumberGenerator {
    //instantiationg variable for incremental account numbersr
    private static int nextAccountNo = 1001;

    /**
     * Method for generation account numbers by incrementation of 1
     * @return new account number
     */
    public static int generateAccountNumber() {
        return nextAccountNo++;
    }
}
