#include<bits/stdc++.h> 
  
using namespace std; 

int count( int S[], int m, int n )  
{  
    
    int table[m+1][n+1];  
    
memset(table, 0, sizeof(table));  

    for(int i=0;i<m;i++) 
   {table[0][i] = 1;  
   } 
      
    for(int i=1;i<=m;i++) 
      { 
            
          for(int j=1;j<=n;j++) 
          { 
              if(S[i-1]>j) 
              { 
                  table[i][j]=table[i-1][j]; 
                    
              } 
                
              else
              { 
                  table[i][j]=table[i-1][j]+table[i][j-(i-1)]; 
              } 
                
          } 
      } 
    return table[m][n];  
} 

int main() 
{ 
    int arr[] = {1, 2, 3}; 
    int m = sizeof(arr)/sizeof(arr[0]); 
    int n = 4; 
    cout << count(arr, m, n); 
    return 0; 
} 