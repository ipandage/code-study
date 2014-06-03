/***
 * Excerpted from "Programming Concurrency on the JVM",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vspcon for more book information.
***/
package gao.study.concurrency.jvm.divideAndConquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.io.IOException;

public class ConcurrentNAVVaryThread extends AbstractNAV {
  final int poolSize;
  
  public ConcurrentNAVVaryThread(int thePoolSize) {
    poolSize = thePoolSize;  
  }
  
  public double computeNetAssetValue(final Map<String, Integer> stocks) 
      throws ExecutionException, InterruptedException {
      
    List<Callable<Double>> partitions = new ArrayList<Callable<Double>>();
    for(String ticker : stocks.keySet()) {
      final String theTickerSymbol = ticker;
      partitions.add(new Callable<Double>() {
        public Double call() throws Exception {
          return stocks.get(theTickerSymbol).intValue() * 
            YahooFinance.getPrice(theTickerSymbol);
        }        
      });
    }
    
    
    ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);    
    List<Future<Double>> valueOfStocks = executorPool.invokeAll(
      partitions, 10000, TimeUnit.SECONDS);
    executorPool.shutdown();
      
    double netAssetValue = 0.0; 
    for(Future<Double> valueOfAStock : valueOfStocks) { 
      netAssetValue += valueOfAStock.get(); 
    }
    return netAssetValue;   
  } 

  public void timeAndComputeValue() 
    throws ExecutionException, IOException, InterruptedException { 
    final long start = System.nanoTime();
    
    Map<String, Integer> stocks = readTickers();
    double nav = computeNetAssetValue(stocks);    
    
    final long end = System.nanoTime();

    System.out.println(poolSize + " " + (end - start)/1.0e9);
  }

  public static void main(final String[] args) throws Exception {
    new ConcurrentNAVVaryThread(Integer.parseInt(args[0])).timeAndComputeValue();
  }
}
