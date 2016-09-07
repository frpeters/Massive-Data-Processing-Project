package uchile;
 
import java.io.IOException;
 
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
 
public class AirlineDelayByStartTime {
 
 public static void main(String[] args) throws IOException {
 
  // Check if the number of parameters passed to program is 2
  if (args.length != 2) {
   System.err
     .println("Usage: AirlineDelayByStartTime <input path> <output path>");
   System.exit(-1);
  }
 
  // Create the JobConf instance and specify the job name
  JobConf conf = new JobConf(AirlineDelayByStartTime.class);
  conf.setJobName("Airline Delay");
 
  // First and second arguments are input and output folder
  FileInputFormat.addInputPath(conf, new Path(args[0]));
  FileOutputFormat.setOutputPath(conf, new Path(args[1]));
 
  // Specify the mapper and the reducer class
  conf.setMapperClass(AirlineDelayByStartTimeMapper.class);
  conf.setReducerClass(AirlineDelayByStartTimeReducer.class);
 
  // Specify the output key and value of the entire job
  conf.setOutputKeyClass(Text.class);
  conf.setOutputValueClass(IntWritable.class);
 
  // Specify the number of reducers tasks to run at any instant on a
  // machine, defaults to one
  conf.setNumReduceTasks(1);
 
  // Trigger the mapreduce program
  JobClient.runJob(conf);
 }
}