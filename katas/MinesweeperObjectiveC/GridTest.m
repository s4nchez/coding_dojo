#import "GridTest.h"

@implementation GridTest

-(void) setUp
{
	grid =[[Grid alloc] initWithX: 3 andY: 3];
}

-(void) tearDown
{
	[grid release];

}

- (void) testPlacingBomb
{
	[grid placeBombAtX: 0 andY: 0];
	STAssertEquals(1, [grid getBombCount], @"Invalid bomb count");
}

- (void) testGettingAllBombs
{
	[grid placeBombAtX: 0 andY: 0];
	[grid placeBombAtX: 1 andY: 1];
	NSArray *bombs = [grid getBombs];
	NSUInteger expected = 2;
	STAssertEquals(expected, [bombs count], @"Invalid bomb count");
	NSLog(@"CURRENT GRID: \n%@", grid);
}

- (void) testGettingBombsWithAdjacentBombs
{
	[grid placeBombAtX: 1 andY: 1];
	[grid placeBombAtX: 0 andY: 0];
	Cell *c01 = [grid cellAtX: 0 andY:1];
	STAssertEquals(2, [c01 bombsAround], @"Cell has incorrect number of adjacent bombs");
}

- (void) finallyAPassingTest
{
	STAssertEquals(1,1,@"cmon baby");
}

@end
