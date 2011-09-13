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
        valueAt = function(row, col) {
            if (grid && grid.length && grid[row] && grid[row].length > 0)
                return grid[row][col];
            return undefined;
        },
        generateGrid = function(strategy) {
            var i, j, newGrid = [];
            for (i = 0; i < size; i++) {
                newGrid.push([]);
                for (j = 0; j < size; j++) {
                    newGrid[i].push(strategy(valueAt(i, j), i, j));
                }
            }
            return newGrid;
        },
        nextStateForCell = function(cell, row, col) {
            var neighbours = result.neighbours(row, col),
                nextValue = life.nextStep(cell, neighbours);
            result.trigger("cellChanged", row, col, nextValue);
            return nextValue;
        },
        grid = generateGrid(initialStateStrategy);

    _.extend(result, Backbone.Events);

    result.neighbours = function (row, col) {
        var gRow, gCol, result = 0, sameCell;
        for (gRow = row - 1; gRow <= row + 1; gRow++) {
            for (gCol = col - 1; gCol <= col + 1; gCol++) {
                sameCell = gRow === row && gCol === col;
                if (!sameCell && valueAt(gRow, gCol) === life.livingCell) {
                    result++;
                }
            }
        }
        return result;
    };

    result.updateCell = function(row, col, newCell) {
        grid[row][col] = newCell;
    };


    result.tick = function() {
        grid = generateGrid(nextStateForCell);
    };

    return result;
};

jQuery.fn.extend({
    gameOfLifeWidget: /** @this {!Object} */function (eventSource) {
        return this.each(
            function () {
                var widget = jQuery(this);
                eventSource.bind("cellChanged", function(row, col, newValue) {
                    var cell = widget.find("tr:eq(" + row + ") td:eq(" + col + ")");
                    cell.toggleClass('live', newValue);
                });
            }
        );
    }
});

jQuery(function() {
    var initialStrategy = function(previousValue, row, col) {
        var grid = [
                [0,1,0],
                [0,1,0],
                [0,1,0]
        ];
        return grid[row][col] !== 0;
    };

    var grid = life.createGrid(initialStrategy);

    jQuery("#gameOfLive").gameOfLifeWidget(grid);

    setInterval(grid.tick, 2000);

});