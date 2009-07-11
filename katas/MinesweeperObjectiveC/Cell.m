#import "Cell.h"

@implementation Cell

@synthesize x;
@synthesize y;
@synthesize bombsAround;
@synthesize isBomb;

-(Cell*) initWithX: (int)aX andY: (int)aY
{
	[super init];
	self.x = aX;
	self.y = aY;
	self.bombsAround = 0;
	return self;
}

- (NSString*) description
{
	if(isBomb == YES)
	{
		return @"*";
	}
	return [NSString stringWithFormat: @"%d", [self bombsAround]];
}


@end
