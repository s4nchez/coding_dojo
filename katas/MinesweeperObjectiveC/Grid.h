#import <Cocoa/Cocoa.h>
#import "Cell.h"

@interface Grid : NSObject {
	@private 
	NSMutableArray *cells;
}

-(Grid*) initWithX: (int) x andY: (int) y;
-(void) placeBombAtX: (int) x andY: (int) y;
-(int) getBombCount;
-(NSMutableArray*) getBombs;
-(Cell*) cellAtX: (int) x andY: (int) y;
-(void) updateCellsAroundX: (int) x andY: (int) y;

@end
