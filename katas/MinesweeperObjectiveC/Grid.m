#import "Grid.h"
#import "Cell.h"

@implementation Grid

-(Grid*) initWithX: (int)x andY: (int)y
{
	self = [super init];
	cells = [[NSMutableArray alloc]init];	
	int rowCount, colCount;
	for(rowCount = 0; rowCount < x; rowCount++)
	{
		NSMutableArray *row = [[NSMutableArray alloc]init];
		for(colCount = 0; colCount < y; colCount++)
		{
			[row addObject: [[Cell alloc]initWithX: x andY: y]];
		}
		[cells addObject: row];
	}
	return self;
}

-(void) placeBombAtX: (int) x andY: (int) y
{
	NSMutableArray *row = [cells objectAtIndex: x];
	Cell *cell = [row objectAtIndex: y];
	[cell setIsBomb: true];
	[self updateCellsAroundX: x andY: y];
}

-(int) getBombCount
{
	return [self.getBombs count];
}

-(NSMutableArray*) getBombs
{
	NSMutableArray *result = [[NSMutableArray alloc]init];
	int rowCount, colCount;
	for(rowCount = 0; rowCount < [cells count]; rowCount++)
	{
		NSMutableArray *row = [cells objectAtIndex: rowCount];
		for(colCount = 0; colCount < [row count]; colCount++)
		{
			Cell *cell = [row objectAtIndex: colCount];
			if( [cell isBomb] == YES)
			{
				[result addObject: cell];
			}
		}
	}	
	return result;
}

-(NSString *)description
{
	NSMutableString *result = [[NSMutableString alloc]init];
	int rowCount, colCount;
	for(rowCount = 0; rowCount < [cells count]; rowCount++)
	{
		NSMutableArray *row = [cells objectAtIndex: rowCount];
		for(colCount = 0; colCount < [row count]; colCount++)
		{
			Cell *cell = [row objectAtIndex: colCount];
			[result appendFormat:@"%@", cell];
		}
		[result appendString: @"\n"];

	}	
	return result;
}

-(Cell*) cellAtX: (int) x andY: (int) y
{
	return [[cells objectAtIndex: x] objectAtIndex: y];
}

- (void) updateCellsAroundX: (int) x andY: (int) y
{
	int cX, cY;
	for(cX = x - 1; cX <= x + 1; cX++)
	{
		for(cY = y - 1; cY <= y + 1; cY++)
		{
			if(cX >= 0 && cY >= 0 && 
			   cX < [cells count] && cY < [[cells objectAtIndex: 0] count] &&
			   !(cX == x && cY == y))
			{
				Cell *cell = [self cellAtX: cX andY: cY];
				int bCount = [cell bombsAround];
				[cell setBombsAround: bCount+1];
			}
		}
	}
}


@end
