require 'src/tictactoe'
require 'src/player'

describe TicTacToe do
  
  before(:each) do
    @playerX = Player.new
    @playerO = Player.new
    @ttt = TicTacToe.new (@playerX, @playerO)   
  end
  
  it "a game is over when all fields are taken" do
    @ttt.fields = [];
    @ttt.game_over.should == true
  end
  
   it "a game is over when all fields in a column are taken by a player" do
    @playerX.column_taken = true
    @ttt.game_over.should == true
  end
  
   it "a game is over when all fields in a row are taken by a player" do
     @playerX.row_taken = true
     @ttt.game_over.should == true
   end
   
   it "a game is over when all fields in a diagonal are taken by a player" do
      @playerX.diagonal_taken = true
      @ttt.game_over.should == true
    end
    
    it " a player can take a field if not already taken" do
      @ttt.fields = ["11"]
      @playerX.take_field("11")
      @ttt.fields.should == []
    end
    
    it "players take turns taking fields until the game is over" do
      @playerX.take_field("11")
      @ttt.game_over.should == false
      @playerO.take_field("21")
      @ttt.game_over.should == false
      @playerX.take_field("12")
      @ttt.game_over.should == false
      @playerO.take_field("22")
      @ttt.game_over.should == false
      @playerX.take_field("13")
      @ttt.game_over.should == true     
    end
  
end