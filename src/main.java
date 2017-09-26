import java.util.* ;

public class main {

    private static int hit(int currentHand) {
        int newCardValue ;
        String newCardName = "" ;
        Random randGen = new Random();

        newCardValue = randGen.nextInt(13);
        newCardValue++;
        if (newCardValue > 10 || newCardValue == 1) {
            switch (newCardValue) {
                case 1:
                    newCardName = "ACE";
                    break;
                case 11:
                    newCardName = "JACK";
                    newCardValue = 10;
                    break;
                case 12:
                    newCardName = "QUEEN";
                    newCardValue = 10;
                    break;
                case 13:
                    newCardName = "KING";
                    newCardValue = 10;
                    break;

            }
            System.out.println("Your card is a " + newCardName + "!");
        }
        else {
            System.out.println("Your card is a " + newCardValue + "!");

        }
        currentHand = currentHand + newCardValue;
        System.out.println("Your hand is: " + currentHand);
        return currentHand ;
    }
    private static int makeDealersHand() {
        int dealerCard ;
        int dealerHand = 0;
        while (dealerHand <= 16) {
            Random randGen = new Random();
            dealerCard =  randGen.nextInt(13) ;
            dealerCard++;
            if ( dealerCard > 10 ) {
                dealerCard = 10;
            }
            dealerHand = dealerHand + dealerCard;
        }
        return dealerHand ;

    }
    public static void main(String args[]) {
        Scanner scrn = new Scanner(System.in);
        int chosenOption;
        int gameNumber = 1;
        int currentHand = 0;
        int dealersHand ;
        int playerWins =0;
        int dealerWins = 0;
        int ties = 0;

        System.out.println("\nStart Game #" + gameNumber + "\n");
        currentHand = hit(currentHand);
        while(true) {
            System.out.println("\n1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit\n\nChoose an option: ");
            try {
                chosenOption = scrn.nextInt();
            }
            catch(Exception oops) {
                chosenOption = 5;
                scrn.nextLine();
            }
            switch (chosenOption) {
                case 1:
                    currentHand = hit(currentHand);
                    if (currentHand > 21) {
                        System.out.println("\nYou exceeded 21! You lost:(");
                        dealerWins += 1;
                        currentHand = 0;
                        System.out.println("\nStart Game #" + ++gameNumber);
                    }
                    break;
                case  2:
                    dealersHand = makeDealersHand();
                    System.out.println("\nDealer's hand: " + dealersHand + "\nYour hand is: " + currentHand );
                    if (dealersHand > 21 || dealersHand < currentHand) {
                        System.out.println("\nYou win!");
                        playerWins += 1;
                        currentHand = 0;
                        System.out.println("\nStart Game #" + ++gameNumber);
                    }
                    else if (dealersHand > currentHand){
                        System.out.println("\nDealer wins!");
                        dealerWins += 1;
                        currentHand = 0;
                        System.out.println("\nStart Game #" + ++gameNumber);
                    }
                    else {
                        System.out.println("\nIt's a tie! No one wins!");
                        ties += 1;
                        currentHand = 0;
                        System.out.println("\nStart Game #" + ++gameNumber);
                    }
                    break;
                case 3:
                    System.out.println("\nNumber of Player wins: " + playerWins);
                    System.out.println("Number of Dealer wins: " + dealerWins);
                    System.out.println("Number of tie games: " + ties);
                    System.out.println("Total # of games played is: " + (gameNumber-1));
                    System.out.format("Percentage of Player wins: %.1f%%\n" , (float)playerWins/(gameNumber-1)*100);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nInvalid input!\nPlease enter an integer value between 1 and 4.");
            }
        }
    }
}
