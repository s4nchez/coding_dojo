class TicTacToe
  attr_accessor :fields
  
  def initialize(playerX, playerO)
    @playerX = playerX
    @playerX.ticTacToe = self;
    @playerO = playerO
    @playerO.ticTacToe = self;
    @fields = []
    (1..3).each { |row| 
      (1..3).each {|col|
        @fields << row.to_s+col.to_s
      }
    }
  end
  
  def game_over
    @fields.length == 0 \
      || @playerX.column_taken || @playerO.column_taken \
      || @playerX.row_taken || @playerO.row_taken \
      || @playerX.diagonal_taken || @playerO.diagonal_taken
  end
end