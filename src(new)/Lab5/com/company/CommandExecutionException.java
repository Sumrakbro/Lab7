package Lab5.com.company;;

public class CommandExecutionException extends Exception{
    public CommandExecutionException(String message){
        super(message);
    }
    public CommandExecutionException(){
        super();
    }
}
