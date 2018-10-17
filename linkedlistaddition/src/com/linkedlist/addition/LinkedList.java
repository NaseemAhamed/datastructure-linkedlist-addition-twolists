package com.linkedlist.addition;

public class LinkedList {
	class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	Node head1, head2, result;
	int carry;

	void push(int data, int type) {
		Node newNode = new Node(data);
		if (type == 1) {
			newNode.next = head1;
			head1 = newNode;
		}
		if (type == 2) {
			newNode.next = head2;
			head2 = newNode;
		}
		if (type == 3) {
			newNode.next = result;
			result = newNode;
		}

	}

	void calculateSum(Node node1, Node node2) {
		if (node1 == null) {
			return;
		}
		calculateSum(node1.next, node2.next);
		int sum = node1.data + node2.data + carry;
		carry = sum / 10;
		sum = sum % 10;

		push(sum, 3);
	}

	Node cur;

	int getSize(Node head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;

	}

	void propogateCarry(Node head1) {
		if (head1 != cur) {
			int sum = carry + head1.data;
			carry = sum / 10;
			sum = sum % 10;
			push(sum,3);
		}
		
	}

	void sumList() {

		if (head1 == null) {
			result = head2;
			return;
		}
		if (head2 == null) {
			result = head1;
			return;
		}

		int size1 = getSize(head1);
		int size2 = getSize(head2);
		if (size1 == size2) {
			calculateSum(head1, head2);
		} else {
			if (size2 > size1) {
				Node temp = head1;
				head1 = head2;
				head2 = temp;

			}
			int diff = Math.abs(size1 - size2);
			Node temp = head1;
			while (diff-- >= 0) {
				cur = temp;
				temp = temp.next;
			}

			calculateSum(cur, head2);
			propogateCarry(head1);

		}
		if (carry > 0) {
			push(carry, 3);
		}

	}
	void printList(Node head)
	{
		System.out.println();
		while(head!=null)
		{
			System.out.print(head.data+ " ");
			head=head.next;
		}
		
	}
	public static void main(String args[])
	{
		
		LinkedList list=new LinkedList();
		list.head1=null;
		list.head2=null;
		list.result=null;
		list.carry=0;
		int arr1[]= {9,1,2};
		int arr2[]= {1,8};
		
		for(int i=arr1.length-1;i>=0;--i)
			list.push(arr1[i],1);
		
		list.printList(list.head1);
		
		for(int i=arr2.length-1;i>=0;--i)
			list.push(arr2[i], 2);
		
		list.printList(list.head2);
		
		
		list.sumList();
		list.printList(list.result);
		
	}

}
