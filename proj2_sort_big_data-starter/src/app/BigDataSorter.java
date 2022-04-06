// package app;
// import sort.MergeSort;

// public class BigDataSorter {
//   // The size of initial segments. This is the largest amount of data that 
//   // this code can store at any time.
//   public static final int MAX_ARRAY_SIZE = 43;  
//   private DataSourceAccessor<Integer> dataAccessor;
//   // temporary file names
//   private String tempFile1 = "temp1.dat";
//   private String tempFile2 = "temp2.dat";
//   private String tempFile3 = "temp3.dat";
  
//   // Initialize the data accessor object.
//   public BigDataSorter(DataSourceAccessor<Integer> da){
//       dataAccessor = da;
//   }
  
//   /*
//    * High-level method to carry out the sort. Calls initSegments and merge
//    * to carry out phase 1 and phase 2 respectively.
//    */
//   public void sort() throws Exception {
//     // Implement Phase 1: Create initial segments
//     int numberOfSegments = initSegments(MAX_ARRAY_SIZE);
//     // Implement Phase 2: Merge segments recursively
//     merge(numberOfSegments, MAX_ARRAY_SIZE, tempFile1, tempFile2, tempFile3);
//   }

//   /*
//    * Implements phase 1. Data from the original file is processed in segments of 
//    * size MAX_ARRAY_SIZE. To process a segment, tempFile1 
//    * is opened for write, then data from the original file (which is already open for read)
//    * is read in and stored in an array of ints of size MAX_ARRAY_SIZE. 
//    * This array is sorted, and then read into tempFile1. This process is repeated until 
//    * there is no more data in the original file. Note that the last segment may not be full.
//    * A counter keeps track of the number of segments. This number is returned. 
//    * Use MergeSort (in package sort) to sort the segments you read in to the array. 
//    */
//   // public int initSegments (int initSegmentSize) throws Exception {
//   //   int numberOfSegments = 0;
//   //   dataAccessor.openTempDSforWrite(tempFile1);
    
    
//   //   int[] FromF1 = new int[MAX_ARRAY_SIZE];
    
//   //     while(dataAccessor.origSrcHasNext()==true){
       
//   //        for(int i=0; i<MAX_ARRAY_SIZE && dataAccessor.origSrcHasNext(); i++) {
//   //           FromF1[i] = dataAccessor.origSrcGetNext();
//   //        }
//   //   //call mergesort to sort the array FromF1
//   //       MergeSort.mergeSort(FromF1, 0, FromF1.length-1);
    
//   //    //write integers in FromF1 into tempFile1
//   //        for (int i = 0; i < FromF1.length; ++i) {
//   //           dataAccessor.tempDSwriteNext(tempFile1, FromF1[i]);
//   //        }
//   //     numberOfSegments ++;
//   //     }
      
//   //     dataAccessor.tempDSclose(tempFile1);
//   //     return numberOfSegments;
//   // }

//   public int initSegments(int initSegmentSize) throws Exception {
//     int numberOfSegments = 0;
//     dataAccessor.openTempDSforWrite(tempFile1);

//     int[] FromF1 = new int[MAX_ARRAY_SIZE];
//     while (dataAccessor.origSrcHasNext() == true) {
//         int k = 0;
//         for (int i = 0; i < MAX_ARRAY_SIZE && dataAccessor.origSrcHasNext(); i++) {
//             FromF1[i] = dataAccessor.origSrcGetNext();
//             k = i;
//            // System.out.print(numberOfSegments + "-" + i + ":" + FromF1[i] + " ");
//         }
//         //System.out.println("");

//         //call mergesort to sort the array FromF1
//         MergeSort.mergeSort(FromF1, 0, k);

//         //write integers in FromF1 into tempFile1
//         for (int i = 0; i <= k; ++i) {
//             dataAccessor.tempDSwriteNext(tempFile1, FromF1[i]);
//         }
//         numberOfSegments++;
//     }
//     dataAccessor.tempDSclose(tempFile1);
//     return numberOfSegments;
// }
  
 
    

//   /*
//    * Recursively merge the sorted segments until there is only 1 segment left.
//    * Then, copy the file with the single, sorted segment to the result file.
//    * This method calls mergeOneStep to carry out a merge, then makes the recursive
//    * call to merge, where the number of segments has decreased by half, and the
//    * segment size has doubled. The temporary files shift as well. The sorting is done 
//    * when there is only one segment. The sorted data is in the file that f1 references, 
//    * so copy f1 to the final sorted file.
//    */
//   public void merge(int numberOfSegments, int segmentSize, 
//                       String f1, String f2, String f3) throws Exception {
//     if (numberOfSegments > 1) {
//       mergeOneStep(numberOfSegments, segmentSize, f1, f2, f3);
//       merge((numberOfSegments + 1) / 2, segmentSize * 2, f3, f1, f2);
//     }    
//     else { //done sorting. 
//        dataAccessor.openSortedForWrite();
//        copyToResultFile(f1);
//     }
//   }

//   /*
//    * This method carries out the work of doing a merge operation. The first step is
//    * to open file f1 for read, f2 for write, then call copyHalfFromF1ToF2 to write
//    * the first half of the segments in f1 to f2, then f2 is closed. File f1 is left 
//    * open because it will be used in megeing segments and it's file curor is at the 
//    * halfway point in the file after the copy procedure. The next step is to 
//    * merge all segments in the second half of f1 with those in f2 and write the merged 
//    * segments to f3. To do this, file f2 is open for read and f3 is open for write. Then,
//    * mergeSegments is called with half the number of segments.
//    * Finally, f1, f2, and f3 are closed.
//    */ 
 


//   public void mergeOneStep(int numberOfSegments, int segmentSize,
//                          String f1, String f2, String f3) throws Exception {

//     //copy first half of f1 to f2
//     dataAccessor.openTempDSforRead(f1);
//     dataAccessor.openTempDSforWrite(f2);
//     copyHalfFromF1ToF2(numberOfSegments, segmentSize, f1, f2); //????????????????
//     dataAccessor.tempDSclose(f2);

//     //merge 2nd half of f1 with f2, write merged segment to f3
//     dataAccessor.openTempDSforRead(f2);
//     dataAccessor.openTempDSforWrite(f3);
//     mergeSegments(numberOfSegments / 2, segmentSize/2, f1, f2, f3);//????????????????
//     dataAccessor.tempDSclose(f1);
//     dataAccessor.tempDSclose(f2);
//     dataAccessor.tempDSclose(f3);


// }

//   /* Copy the first half of the number of segments from f1 to f2.
//    * Assume f1 is open for read, f2 is open for write. */
//   public void copyHalfFromF1ToF2(int numberOfSegments, int segmentSize, 
//                                   String f1, String f2) throws Exception {
  
    
        
   
//       //   int numberOfGroup = 1;
//       //   while(numberOfGroup <= numberOfSegments/2){
//       //      for(int i=0; i<segmentSize && dataAccessor.tempDShasNext(f1); i++) {
//       //        dataAccessor.tempDSwriteNext(f2, dataAccessor.tempDSgetNext(f1));
//       //     }
//       //      numberOfGroup++;
//       //   }
//       // }
//       for (int i = 0; i < (numberOfSegments / 2) * segmentSize; i++)
//       dataAccessor.tempDSwriteNext(f2, dataAccessor.tempDSgetNext(f1));

// }
   

//   /* Merge all segments. Calls mergeTwoSegments until all but possibly the last
//    * segment have been merged. If an odd segment remains in f1 it is written to f3.
//    * Assume f1, f2 open for read, f3 for write.
//   */
//   public void mergeSegments(int numberOfSegments, int segmentSize, 
//                              String f1, String f2, String f3) throws Exception {
    
//     for (int i = 0; i < numberOfSegments; i++) {
//       mergeTwoSegments(segmentSize, f1, f2, f3);
//     }
//     // If f1 has one extra segment, copy it to f3
//     while (dataAccessor.tempDShasNext(f1)) {
//        dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f1));
//     }
//   }

//   /* Merges two segments from f1 and f2, write the merged segments to f3. This is the 
//    * basic merge algorithm from merge sort, except this version uses file streams 
//    * instead of arrays. That means the segment size has to be tracked so the merge 
//    * does not access integers outside of the segmentSize. 
//    * See the instructions for more on this adaptation.
//    */
  
//   public void mergeTwoSegments(int segmentSize, String f1, String f2, String f3) throws Exception {
    
    
//   //      for (int i = 0; i < segmentSize; i++) {
//   //        for (int j = 0; j < segmentSize && dataAccessor.tempDShasNext(f1); j++) {
//   //           if(dataAccessor.tempDSgetNext(f2) <= dataAccessor.tempDSgetNext(f1)){
//   //           dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f2));
//   //           }
        
//   //        dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f1));
//   //        }
//   //      } 
//   // }

 
//     // for (int i = 0; i < segmentSize && dataAccessor.tempDShasNext(f1); i++) {
//     //     //int tem1 = dataAccessor.tempDSgetNext(f1);
//     //     //int tem2 = dataAccessor.tempDSgetNext(f2);
//     //     if (dataAccessor.tempDSgetNext(f1)<dataAccessor.tempDSgetNext(f2)) {
//     //       //int tem1 =  < tem2) {
//     //         dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f1));
//     //     } 
//     //     else {
//     //         dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f2));
//     //     }
//     // }
    
//   //     for (int i = 0; i < segmentSize; i++) {
//   //         int temp2 = dataAccessor.tempDSgetNext(f2);
//   //         if (dataAccessor.tempDShasNext(f1)) {
//   //             int temp1 = dataAccessor.tempDSgetNext(f1);
//   //             if (temp1 < temp2) {
//   //                 dataAccessor.tempDSwriteNext(f3, temp1);
//   //             } else {
//   //                 dataAccessor.tempDSwriteNext(f3, temp2);
//   //             }
//   //         } else {
//   //             dataAccessor.tempDSwriteNext(f3, temp2);
//   //         }
//   //     }
//   // }
//   int intFormF1 = dataAccessor.tempDSgetNext(f1);
//   int intFormF2 = dataAccessor.tempDSgetNext(f2);
//   int f1Count = 1;
//   int f2Count = 1;
//   while (true) {
//       if (intFormF1 < intFormF2) {
//           dataAccessor.tempDSwriteNext(f3, intFormF1);
//           if (!dataAccessor.tempDShasNext(f1) || f1Count++ >= segmentSize) {
//               dataAccessor.tempDSwriteNext(f3, intFormF2);
//               break;
//           } else {
//               intFormF1 = dataAccessor.tempDSgetNext(f1);
//           }
//       } else {
//           dataAccessor.tempDSwriteNext(f3, intFormF2);
//           if (!dataAccessor.tempDShasNext(f2) || f2Count++ >= segmentSize) {
//               dataAccessor.tempDSwriteNext(f3, intFormF1);
//               break;
//           } else {
//               intFormF2 = dataAccessor.tempDSgetNext(f2);
//           }
//       }
//   }
//   while (dataAccessor.tempDShasNext(f1) && f1Count++ < segmentSize) {
//       dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f1));
//   }
//   while (dataAccessor.tempDShasNext(f2) && f2Count++ < segmentSize) {
//       dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f2));
//   }
// }
    

  
//   /*
//    * Copies the data from the file referenced by the argument to the 
//    * sorted file, which should contain all of the original data sorted in 
//    * ascending order.
//    */
//   public void copyToResultFile(String fileName)throws Exception{
//       // assume file closed- but try closing anyway
//       dataAccessor.tempDSclose(fileName);
//       dataAccessor.openTempDSforRead(fileName);
//      // then re-open for read
//       while(dataAccessor.tempDShasNext(fileName))
//          dataAccessor.sortedWriteNext(dataAccessor.tempDSgetNext(fileName));
//       dataAccessor.sortedDataClose();
//       dataAccessor.tempDSclose(fileName);
//   }
  
// }
package app;

import sort.MergeSort;



public class BigDataSorter {
   // The size of initial segments. This is the largest amount of data that
   // this code can store at any time.
   public static final int MAX_ARRAY_SIZE = 43;
   private DataSourceAccessor<Integer> dataAccessor;
   // temporary file names
   private String tempFile1 = "temp1.dat";
   private String tempFile2 = "temp2.dat";
   private String tempFile3 = "temp3.dat";

   // Initialize the data accessor object.
   public BigDataSorter(DataSourceAccessor<Integer> da) {
      dataAccessor = da;
   }

   /*
    * High-level method to carry out the sort. Calls initSegments and merge
    * to carry out phase 1 and phase 2 respectively.
    */
   public void sort() throws Exception {
      // Implement Phase 1: Create initial segments
      int numberOfSegments = initSegments(MAX_ARRAY_SIZE);
      // Implement Phase 2: Merge segments recursively
      merge(numberOfSegments, MAX_ARRAY_SIZE, tempFile1, tempFile2, tempFile3);
   }

   public void displayFile(String file) throws Exception {
      dataAccessor.openTempDSforRead(file);
      try {
         while (dataAccessor.tempDShasNext(file)) {
            System.out.print(dataAccessor.tempDSgetNext(file) + " ");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      dataAccessor.tempDSclose(file);
   }

   /*
    * Implements phase 1. Data from the original file is processed in segments of
    * size MAX_ARRAY_SIZE. To process a segment, tempFile1
    * is opened for write, then data from the original file (which is already open for read)
    * is read in and stored in an array of ints of size MAX_ARRAY_SIZE.
    * This array is sorted, and then read into tempFile1. This process is repeated until
    * there is no more data in the original file. Note that the last segment may not be full.
    * A counter keeps track of the number of segments. This number is returned.
    * Use MergeSort (in package sort) to sort the segments you read in to the array.
    */
   public int initSegments(int initSegmentSize) throws Exception {
      int numberOfSegments = 0;
      dataAccessor.openTempDSforWrite(tempFile1);
   
      int[] FromF1 = new int[initSegmentSize];
      while (dataAccessor.origSrcHasNext() == true) {
         numberOfSegments++;
         int i = 0;
         for (; i < initSegmentSize && dataAccessor.origSrcHasNext(); i++) {
            FromF1[i] = dataAccessor.origSrcGetNext();
         }
      
         //call mergesort to sort the array FromF1
         MergeSort.mergeSort(FromF1, 0, i - 1);
      
         //write integers in FromF1 into tempFile1
         for (int j = 0; j < i; j++) {
            dataAccessor.tempDSwriteNext(tempFile1, FromF1[j]);
         }
      }
      dataAccessor.tempDSclose(tempFile1);
      return numberOfSegments;
   }


   /*
    * Recursively merge the sorted segments until there is only 1 segment left.
    * Then, copy the file with the single, sorted segment to the result file.
    * This method calls mergeOneStep to carry out a merge, then makes the recursive
    * call to merge, where the number of segments has decreased by half, and the
    * segment size has doubled. The temporary files shift as well. The sorting is done
    * when there is only one segment. The sorted data is in the file that f1 references,
    * so copy f1 to the final sorted file.
    */
   public void merge(int numberOfSegments, int segmentSize,
                     String f1, String f2, String f3) throws Exception {
      if (numberOfSegments > 1) {
         mergeOneStep(numberOfSegments, segmentSize, f1, f2, f3);
         merge((numberOfSegments + 1) / 2, segmentSize * 2, f3, f1, f2);
      } else { //done sorting.
         dataAccessor.openSortedForWrite();
         copyToResultFile(f1);
      }
   }

   /*
    * This method carries out the work of doing a merge operation. The first step is
    * to open file f1 for read, f2 for write, then call copyHalfFromF1ToF2 to write
    * the first half of the segments in f1 to f2, then f2 is closed. File f1 is left
    * open because it will be used in megeing segments and it's file curor is at the
    * halfway point in the file after the copy procedure. The next step is to
    * merge all segments in the second half of f1 with those in f2 and write the merged
    * segments to f3. To do this, file f2 is open for read and f3 is open for write. Then,
    * mergeSegments is called with half the number of segments.
    * Finally, f1, f2, and f3 are closed.
    */
   public void mergeOneStep(int numberOfSegments, int segmentSize,
                            String f1, String f2, String f3) throws Exception {
   
      //copy first half of f1 to f2
      dataAccessor.openTempDSforRead(f1);
      dataAccessor.openTempDSforWrite(f2);
      copyHalfFromF1ToF2(numberOfSegments, segmentSize, f1, f2); //????????????????
      dataAccessor.tempDSclose(f2);
   
      //merge 2nd half of f1 with f2, write merged segment to f3
      dataAccessor.openTempDSforRead(f2);
      dataAccessor.openTempDSforWrite(f3);
      mergeSegments(numberOfSegments / 2, segmentSize, f1, f2, f3);//????????????????
      dataAccessor.tempDSclose(f1);
      dataAccessor.tempDSclose(f2);
      dataAccessor.tempDSclose(f3);
   
   
   }

   /* Copy the first half of the number of segments from f1 to f2.
    * Assume f1 is open for read, f2 is open for write. */
   public void copyHalfFromF1ToF2(int numberOfSegments, int segmentSize,
                                  String f1, String f2) throws Exception {
   
      //copy first half in f1 to f2
      for (int i = 0; i < (numberOfSegments / 2) * segmentSize; i++) {
         dataAccessor.tempDSwriteNext(f2, dataAccessor.tempDSgetNext(f1));
      }
   }

   /* Merge all segments. Calls mergeTwoSegments until all but possibly the last
    * segment have been merged. If an odd segment remains in f1 it is written to f3.
    * Assume f1, f2 open for read, f3 for write.
    */
   public void mergeSegments(int numberOfSegments, int segmentSize,
                             String f1, String f2, String f3) throws Exception {
   
      for (int i = 0; i < numberOfSegments; i++) {
         mergeTwoSegments(segmentSize, f1, f2, f3);
      }
      // If f1 has one extra segment, copy it to f3
      while (dataAccessor.tempDShasNext(f1)) {
         dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f1));
      }
   }

   /* Merges two segments from f1 and f2, write the merged segments to f3. This is the
    * basic merge algorithm from merge sort, except this version uses file streams
    * instead of arrays. That means the segment size has to be tracked so the merge
    * does not access integers outside of the segmentSize.
    * See the instructions for more on this adaptation.
    */
   public void mergeTwoSegments(int segmentSize, String f1, String f2, String f3) throws Exception {
      int intFormF1 = dataAccessor.tempDSgetNext(f1);
      int intFormF2 = dataAccessor.tempDSgetNext(f2);
      int f1Count = 1;
      int f2Count = 1;
      while (true) {
         if (intFormF1 < intFormF2) {
            dataAccessor.tempDSwriteNext(f3, intFormF1);
            if (!dataAccessor.tempDShasNext(f1) || f1Count++ >= segmentSize) {
               dataAccessor.tempDSwriteNext(f3, intFormF2);
               break;
            } else {
               intFormF1 = dataAccessor.tempDSgetNext(f1);
            }
         } else {
            dataAccessor.tempDSwriteNext(f3, intFormF2);
            if (!dataAccessor.tempDShasNext(f2) || f2Count++ >= segmentSize) {
               dataAccessor.tempDSwriteNext(f3, intFormF1);
               break;
            } else {
               intFormF2 = dataAccessor.tempDSgetNext(f2);
            }
         }
      }
      while (dataAccessor.tempDShasNext(f1) && f1Count++ < segmentSize) {
         dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f1));
      }
      while (dataAccessor.tempDShasNext(f2) && f2Count++ < segmentSize) {
         dataAccessor.tempDSwriteNext(f3, dataAccessor.tempDSgetNext(f2));
      }
   }

   /*
    * Copies the data from the file referenced by the argument to the
    * sorted file, which should contain all of the original data sorted in
    * ascending order.
    */
   public void copyToResultFile(String fileName) throws Exception {
      // assume file closed- but try closing anyway
      dataAccessor.tempDSclose(fileName);
      dataAccessor.openTempDSforRead(fileName);
      // then re-open for read
      while (dataAccessor.tempDShasNext(fileName))
         dataAccessor.sortedWriteNext(dataAccessor.tempDSgetNext(fileName));
      dataAccessor.sortedDataClose();
      dataAccessor.tempDSclose(fileName);
   }

}



