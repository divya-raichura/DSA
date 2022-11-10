package com.company.questions;

public class Questions {

    private ListNode head = null;

    // q1
    public void insertRec(int val) {
        if (head == null) {
            head = new ListNode(val);
            return;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new ListNode(val);
    }

    public void insertRec(int val, int index) {
        head = insertRec(val, index, head);
    }

    private ListNode insertRec(int val, int index, ListNode node) {
        if (index == 0) {
            return new ListNode(val, node);
        }

        node.next = insertRec(val, index - 1, node.next);
        return node;
    }

    // q2
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head;
        while (temp.next != null) {
            if (temp.next.val == temp.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

        return head;
    }

    // q3
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // if(list1 == null) return list2;
        // if(list2 == null) return list1;

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
//                temp = list1; // not needed these two, instead temp.next for both
                list1 = list1.next;
            } else {
                temp.next = list2;
//                temp = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1 == null) temp.next = list2;
        else temp.next = list1;

        return dummy.next;
    }

    // q4
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    // q5
    public int cycleLength(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode temp = slow;
                int l = 0;
                do {
                    temp = temp.next;
                    l++;
                } while (temp != slow);
                return l;
            }
        }
        return 0;
    }

    // q6
    public ListNode startOfLoop(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode temp = head;
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return slow; // or temp
            }
        }
        return null;
    }

    // q7
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            fast = square(square(fast));
            slow = square(slow);
        } while (fast != slow);
        return fast == 1;
    }

    private int square(int n) {
        int ans = 0;
        while (n > 0) {
            int rem = n % 10;
            ans += (rem * rem);
            n /= 10;
        }
        return ans;
    }

    // q8
    public ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // q9
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return merge(left, right);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if (l1 == null) temp.next = l2;
        else temp.next = l1;

        return dummy.next;
    }

    // q 10
    public ListNode reverseIterative(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode now = head;
        ListNode future = head.next;
        while (now != null) {
            now.next = prev;
            prev = now;
            now = future;
            if (future != null) {
                future = future.next;
            }
        }
        return prev;
    }

    // q 11
    public ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }

    private ListNode reverseList(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode future = head.next;
        head.next = newHead;
        return reverseList(future, head);
    }

    // q 12
//    https://www.youtube.com/watch?v=RF_M9tX4Eag&t=517s
//    my GitHub
    /* // its simple, prev = dummy and curr = head, move ahead till curr != m
    // stop when curr at m and prev one before m
    // t1 points one node before left(m) ie at prev
    // t2 points to left(m) ie at curr
    // then we move and reverse curr and prev till prev != n
    // now, stop when prev is at n. now, prev points to right(n)
    // link t1 with prev and t2 with curr
    */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int i = 1;
        ListNode prev = dummy;
        ListNode curr = head;
        while(left != i) {
            i++;
            prev = prev.next;
            curr = curr.next;
        }

        ListNode holdTail = curr;
        ListNode rprev = null;
        while(i <= right) {
            ListNode temp = curr.next;
            curr.next = rprev;
            rprev = curr;
            curr = temp;
            i++;
        }

//        rprev points to head of reversed ll
//        holdTail points to tail of reversed ll
//        prev points to the end of left half of list where we broke the list

        prev.next = rprev;
        holdTail.next = curr;

        return dummy.next;
    }

    // q 13
    public boolean isPalindrome() {
        return isPalindrome(head);
    }


    private boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode mid = getMid(head);
        ListNode reversedHead = reverseIterative(mid);
        ListNode t1 = head;
        ListNode t2 = reversedHead;
        while (t1 != null && t2 != null) {
            if (t1.val != t2.val) return false;
            t1 = t1.next;
            t2 = t2.next;
        }
        reverseIterative(reversedHead);
        return true;
    }

    // q 14(reorder)
    // neetcode or leetcode
    // or below (sol1)
    // or my below(sol2) from github
    public void reorderList(ListNode head) {

        if(head == null || head.next == null) return;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode mid = mid(head);
        ListNode t = head;
        while(t.next != mid) {
            t = t.next;
        }

        t.next = null;

        ListNode temp = dummy;
        ListNode t1 = dummy.next;
        ListNode t2 = reverseIterative(mid);

        while(t1 != null && t2 != null) {
            temp.next = t1;
            t1 = t1.next;
            temp = temp.next;
            temp.next = t2;
            t2 = t2.next;
            temp = temp.next;
        }

        if(t1 == null) temp.next = t2;
        else temp.next = t1;
    }

    public void reorderList2(ListNode head) {

        if(head == null || head.next == null) return;

        ListNode p = null;
        ListNode f = head;
        ListNode s = head;
        while(f != null && f.next != null) {
            f = f.next.next;
            p = s;
            s = s.next;
        }

        p.next = null;
        ListNode mid = s;

        ListNode head1 = head;
        ListNode head2 = reverseIterative(mid);

        while(head2 != null) {
            ListNode l1 = head1.next;
            ListNode l2 = head2.next;

            head1.next = head2;
            if(l1 == null){
                break;
            }
            head2.next = l1;

            head1 = l1;
            head2 = l2;
        }
    }

    // q 15
    // Best : https://leetcode.com/problems/intersection-of-two-linked-lists/solutions/49785/java-solution-without-knowing-the-difference-in-len/
    // another solution
    public ListNode getIntersectionNodeLeet(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        // move headA and headB to the same start point
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        // find the intersection until end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    // my solution to q 15
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;
        while(t1 != null && t2!= null) {
            t1 = t1.next;
            t2 = t2.next;
        }
        ListNode lesser = t1 == null? t1 : t2;
        ListNode countDiff = t1 == null? t2 : t1;
        int count = 0;
        while(countDiff != null) {
            count++;
            countDiff = countDiff.next;
        }

        if(lesser == t1) {
            t1 = headA;
            t2 = headB;
            while(count != 0) {
                t2 = t2.next;
                count--;
            }
            while(t1 != t2) {
                t1 = t1.next;
                t2 = t2.next;
            }
            return t1;
        } else {
            t1 = headA;
            t2 = headB;
            while(count != 0) {
                t1 = t1.next;
                count--;
            }
            while(t1 != t2) {
                t1 = t1.next;
                t2 = t2.next;
            }
            return t1;
        }
    }

    // q16
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode temp = dummy;
        while(true) {
            ListNode prev = null;
            ListNode curr = temp.next;
            ListNode kNode = getK(curr, k);

            if(kNode == null) break;

            while(prev != kNode) {
                ListNode nxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nxt;
            }

            temp.next.next = curr;
            ListNode savePre = temp.next;
            temp.next = prev;
            temp = savePre;
        }
        return dummy.next;
    }

    private ListNode getK(ListNode node, int n) {
        ListNode c = node;
        while(n != 1) {
            if(c == null) return null;
            c = c.next;
            n--;
        }
        return c;
    }

    // q 17
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        int l = 1;
        ListNode temp = head;
        while(temp.next != null) {
            l++;
            temp = temp.next;
        }
        temp.next = head;
        int newHeadPrevIndex = l - k % l;
        while(newHeadPrevIndex != 0) {
            temp = temp.next;
            newHeadPrevIndex--;
        }
        ListNode newHead = temp.next;
        temp.next = null;
        return newHead;
    }

    // end
    private ListNode mid(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while(f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    public void display() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    private int length(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }
}
