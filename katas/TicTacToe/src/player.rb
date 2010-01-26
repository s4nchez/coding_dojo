class Player
  attr_accessor :column_taken
  attr_accessor :row_taken
  attr_accessor :diagonal_taken
  
  attr_accessor :ticTacToe
  
  def initialize
    @column_taken = false
    @row_taken = false
    @diagonal_taken = false
    @fields = []
  end
  
  def take_field(field)
    @fields <<  @ticTacToe.fields.delete(field)
    
    @row_taken = (@fields & ["11","12","13"]).length == 3 \
                   ||  (@fields & ["21","22","23"]).length == 3 \
                   ||  (@fields & ["31","32","33"]).length == 3 
                   
    @column_taken = (@fields & ["11","21","23"]).length == 3 \
                       ||  (@fields & ["12","22","32"]).length == 3 \
                       ||  (@fields & ["13","23","33"]).length == 3 
    
    @diagonal_taken = (@fields & ["11","22","33"]).length == 3 \
                       ||  (@fields & ["13","22","31"]).length == 3 
  
  end
end