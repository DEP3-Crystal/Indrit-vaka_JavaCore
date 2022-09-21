# Random Chose person

1. make a random chose
  - 2. Check if that has been chosen before
  - 3. If no show that, and add that to beginning of the chosen list
4. In no make a random chose again based on the person priority
4. If we don't have any chose after step 4 se go again to step 1;


About priority
  the last person who enters the chosen list has the lowest priority to get chosen
          10% chance to get chosen
 the priority for existing persons in the chosen lists increases when another one joins.
The priority increment or the % to be chosen depends on the total list of users.
 - lowest 10%
 - Highest 100%
 - 90% will be deference between the lowest and highest, 
 
Now based on the nr of people, we will increment the % it will increment by **90/length**

Result of priority.
the first person who enters the list will have 10% chance to get chosen and for each other person who
joins the list his % will be increased by 90/users.length


Others
      If all the persons gets in the list the fist one who has joined the list will be removed.
 We have the case when someone that is in the list of chosen and gets chosen again.
 In this case this person will be moved the last one in the list, which means it has the lowest priority to get chosen

##  if this one has been chosen before
1,2,3,4...10
1=> 10% chance to be chosen

10=> 100% chance to be chosen

length => 10

increment should be 90/10 => 9%

transforming the index to the priority

so inc in this case will be 8% => 1*8,2*8,...10*8

 The formula will be 90 - (90 / length * chosen index )+1

lets take some ex suppose that len = 10
  1. **for the first el of the list =>** 90 - (90 / 10 * 0)= 90 -(0 ) +1= 91=> 91 will be range of generation nr means 10 % for th nr to be 1
  2. **last el of list =>** 90 - (90/10 * 10) +1 = 90 - (90) +1 = 1 range if from 1-1 meaning 100% to get 1
  3. **element nr 2 of list =>** 90 - (90/10 * 2) +1 = 90 - (18) +1 = 72 range if from 1-72 meaning 28% to get 1
 With 90/length we will get the increment we need to increase the % from one person to other one. 

Ex. 90/20=4 The chances not to get chosen for last person who was chosen last.
