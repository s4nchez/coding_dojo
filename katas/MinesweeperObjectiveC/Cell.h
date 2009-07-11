#import <Cocoa/Cocoa.h>

@interface Cell : NSObject {
	@private
	int x;
	int y;
	int bombsAround;
	BOOL isBomb;
}

@property(readwrite, assign) int x;
@property(readwrite, assign) int y;
@property(readwrite, assign) int bombsAround;
@property(readwrite, assign) BOOL isBomb;


-(Cell*) initWithX: (int)x andY: (int)y;

@end
