var life = {
};

life.livingCell = true;
life.deadCell = false;

life.nextStep = function (cell, neighbours) {
    if (cell === false) {
        return neighbours === 3;
    }
    return neighbours === 2 || neighbours === 3;
};

life.createGrid = function (initialStateStrategy, initialSize) {
    var result = {}, size = initialSize || 3,
        valueAt = function(row, col){
            if(grid && grid.length && grid[row] && grid[row].length > 0)
                return grid[row][col];
            return undefined;
        },
        generateGrid = function(strategy) {
            var i, j, newGrid = [];
            console.log("new grid");
            for (i = 0; i < size; i++) {
                newGrid.push([]);
                for (j = 0; j < size; j++) {
                    newGrid[i].push(strategy(valueAt(i, j), i, j));
                }
                console.log(newGrid[i]);
            }

            return newGrid;
        },
        grid = generateGrid(initialStateStrategy);

    result.neighbours = function (row, col) {
        var gRow, gCol, result = 0, sameCell;
        for (gRow = row - 1; gRow <= row + 1; gRow++) {
            for (gCol = col - 1; gCol <= col + 1; gCol++) {
                sameCell = gRow === row && gCol === col;
                if (!sameCell && valueAt(gRow,gCol) === life.livingCell) {
                    result++;
                }
            }
        }
        return result;
    };

    result.updateCell = function(row, col, newCell) {
        grid[row][col] = newCell;
    };

    result.tick = function(strategy){
        grid = generateGrid(strategy);
    };
    
    return result;
};

