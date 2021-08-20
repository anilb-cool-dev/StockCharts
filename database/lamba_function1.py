import sys
import botocore
import boto3
from botocore.exceptions import ClientError
def lambda_handler(event, context):
   rds = boto3.client('rds')
   try:
       response = rds.stop_db_instance(DBInstanceIdentifier='database-1')
       print('Success :: ')
   except ClientError as e:
       print(e)
   return
   {
       'message' : "Script execution completed. See Cloudwatch logs for complete output"
   }
