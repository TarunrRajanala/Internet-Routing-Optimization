

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission
// Do not add extra imports

import java.util.ArrayList;

public class Program2 {

    /**
     * findMinimumDistance
     *
     * @param problem  - contains the regions, the start region and the end region of the graph
     * @return the minimum distance possible to get from start region to end region in the given problem.
     * Assume the given graph is always connected.
     */
    public int findMinimumRouteDistance(Problem problem) {
        Region startRegion = problem.getStartRegion();
        Region endRegion = problem.getEndRegion();
        ArrayList<Region> regions = problem.getRegions();
        Heap<Region> heap = new Heap<>();
        for (Region region : regions) {
            region.setMinDist(Integer.MAX_VALUE);
            region.setIndex(-1);
        }
        startRegion.setMinDist(0);
        heap.insertNode(startRegion);
        while (heap.getSize() > 0) {
            Region currentRegion = heap.extractMin();
            if (currentRegion == endRegion) {
                return currentRegion.getMinDist();
            }
            for (int i = 0; i < currentRegion.getNeighbors().size(); i++) {
                Region neighbor = currentRegion.getNeighbors().get(i);
                int weight = currentRegion.getWeights().get(i);
                if (neighbor.getMinDist() > currentRegion.getMinDist() + weight) {
                    neighbor.setMinDist(currentRegion.getMinDist() + weight);
                    if (heap.contains(neighbor)) {
                        heap.changeKey(neighbor, neighbor.getMinDist());
                    } else {
                        heap.insertNode(neighbor);
                    }
                }
            }
        }
        return endRegion.getMinDist();
    }
}
