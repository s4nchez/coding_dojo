describe("Game Of Life", function() {
    var livingCell = life.livingCell;
    var deadCell = life.deadCell;

    describe("Grid", function() {
        var deadInitialState = function() {
            return life.deadCell;
        };

        it("should find live neighbours of cell - all neighbours dead", function() {

            var grid = life.createGrid(deadInitialState);
            expect(grid.neighbours(1, 1)).toEqual(0);
        });

        it("should find live neighbours of cell - one alive", function() {
            var grid = life.createGrid(deadInitialState);
            grid.updateCell(0, 0, livingCell);
            expect(grid.neighbours(1, 1)).toEqual(1);
        });

        it("should find live neighbours of cell - all alive", function() {
            var grid = life.createGrid(function() {
                return life.livingCell;
            });
            expect(grid.neighbours(1, 1)).toEqual(8);
        });

        it("should find live neighbours of cell - ignores itself", function() {
            var grid = life.createGrid(deadInitialState);
            grid.updateCell(0, 0, livingCell);
            grid.updateCell(1, 1, livingCell);
            expect(grid.neighbours(1, 1)).toEqual(1);
        });

        it("should get neighbours and calculate next result", function() {
            var grid = life.createGrid(deadInitialState),
            eventSpy = jasmine.createSpy();
            grid.updateCell(1, 1, livingCell);
            grid.bind("cellChanged", eventSpy);
            grid.tick();
            expect(eventSpy).toHaveBeenCalled();
        })
    });

    describe("Cell", function() {
        beforeEach(function() {
            this.addMatchers({
                toBeAlive: function() {
                    return this.actual === true;
                },
                toBeDead: function() {
                    return this.actual === false;
                }
            })
        });


        it("live cell with fewer than two live neighbours dies", function() {
            expect(life.nextStep(livingCell, 0)).toBeDead();
            expect(life.nextStep(livingCell, 1)).toBeDead();
        });

        it("live cell with two or three live neighbours lives", function() {
            expect(life.nextStep(livingCell, 2)).toBeAlive();
            expect(life.nextStep(livingCell, 3)).toBeAlive();
        });

        it("live cell with more than three live neighbours dies", function() {
            expect(life.nextStep(livingCell, 4)).toBeDead();
            expect(life.nextStep(livingCell, 5)).toBeDead();
            expect(life.nextStep(livingCell, 6)).toBeDead();
            expect(life.nextStep(livingCell, 7)).toBeDead();
            expect(life.nextStep(livingCell, 8)).toBeDead();
        });

        it("dead cell with exactly three live neighbours becomes a live cell", function() {
            expect(life.nextStep(deadCell, 0)).toBeDead();
            expect(life.nextStep(deadCell, 1)).toBeDead();
            expect(life.nextStep(deadCell, 2)).toBeDead();
            expect(life.nextStep(deadCell, 3)).toBeAlive();
            expect(life.nextStep(deadCell, 4)).toBeDead();
            expect(life.nextStep(deadCell, 5)).toBeDead();
            expect(life.nextStep(deadCell, 6)).toBeDead();
            expect(life.nextStep(deadCell, 7)).toBeDead();
            expect(life.nextStep(deadCell, 8)).toBeDead();
        });
    });

    describe("Game of Life widget", function(){
        it("should change class of particular cell", function(){
            var eventSource = {};
            _.extend(eventSource, Backbone.Events);
            jQuery("#testGameOfLife").gameOfLifeWidget(eventSource);

            eventSource.trigger("cellChanged", 1, 1, true);
            expect(jQuery("#testGameOfLife tr:eq(1) td:eq(1)").hasClass("live")).toBeTruthy();

            eventSource.trigger("cellChanged", 2, 2, false);

            expect(jQuery("#testGameOfLife tr:eq(2) td:eq(2)").hasClass("live")).toBeFalsy();
        })
    });
});
