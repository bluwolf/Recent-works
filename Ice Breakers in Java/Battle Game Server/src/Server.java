

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(10000);
        System.out.println("Game Server is Running");
        try {
            while (true) {
                Game game = new Game();
                Game.Player playerX = game.new Player(listener.accept(), 'X');
                Game.Player playerO = game.new Player(listener.accept(), 'O');
                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();              
                playerO.start();
            }
        } finally {
            listener.close();
        }
    }
}


class Game {

	JFrame frame;
    Player currentPlayer;

 
    public boolean hasWinner() {
    	
        return currentPlayer.opponent.blood==0;
    }


    public boolean tie() {   	
        return (currentPlayer.opponent.blood==0) && (currentPlayer.blood ==0);
    }

    public  synchronized boolean legalMove(Player player, int bloodOff) {
//    	if(true){
        if (player == currentPlayer && currentPlayer.blood>0) {
        	System.out.println("Is legal move");
        	(currentPlayer.opponent).blood =(currentPlayer.opponent).blood-bloodOff;
            currentPlayer = currentPlayer.opponent;
            currentPlayer.otherPlayerMoved();
            return true;
        }
        return false;
    }


    class Player extends Thread {
        char mark;
        Player opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        int blood =10;


        public Player(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }


        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }


        public void otherPlayerMoved() {
            output.println("OPPONENT_MOVED " + 1);
            output.println(
                hasWinner() ? "DEFEAT" : tie() ? "TIE" : "");
        }

        public void run() {
            try {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");
                output.println("RESET");

                if (mark == 'X') {
                    output.println("MESSAGE Your move");
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command.startsWith("MOVE")) {
                        int bloodOff = Integer.parseInt(command.substring(5));
                        System.out.println("bloodoff: -"+bloodOff);
                        if((this.opponent).blood<=0){
                        	JOptionPane.showMessageDialog(frame, "You Won.");
                        	
                        }
                        else if (legalMove( this, bloodOff)) {
//                        	System.out.println("Opponent blood: "+(this.opponent).blood);
                            output.println("VALID_MOVE1 " +(this.opponent).blood);
                            output.println("VALID_MOVE2 " +this.blood);
                            
//                            if(hasWinner()) output.println("VICTORY ");
                            output.println(hasWinner() ? "VICTORY"
                                         : tie() ? "TIE"
                                         : "");
                        } 
//                            else {
//                            output.println("MESSAGE Move not Legal. You moved twice, there is a Winner or there is a tie");
//                        }
                        } else if (command.startsWith("QUIT")) {
                        return;
                    }
                }
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }
}