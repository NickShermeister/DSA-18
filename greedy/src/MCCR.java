import java.util.PriorityQueue;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // TODO
            IndexPQ<Integer> ipq = new IndexPQ<>(G.numberOfV());
            int inf = Integer.MAX_VALUE;
            for(int i = 0; i < G.numberOfV(); i++){
                ipq.insert(i, Integer.MAX_VALUE);
            }

            ipq.changeKey(0, 0);
            int total = 0;
            int temp;
            Iterable<Edge> edgie;
            while(!ipq.isEmpty()){
                total += ipq.minVal();
                temp = ipq.delMin();
                edgie = G.edges(temp);
                for(Edge hi : edgie){
                    try{
                        ipq.decreaseKey(hi.other(temp), hi.weight());
                    }
                    catch (Exception e){

                    }
                }
            }
            return total;
        }


    }

