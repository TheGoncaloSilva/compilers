/*
 *  \author All
 */

#ifndef __SOMM22__MODULE__MEM__GROUP__
#define __SOMM22__MODULE__MEM__GROUP__

#include "somm22.h"
#include <list>


namespace somm22
{
    
    namespace group 
    {

        namespace mem
        {	
			struct block{
				uint32_t pid;
				void* initialAddr;
				uint32_t size; 
			};

            extern uint32_t maxSize;
            extern AllocationPolicy allocationPolicy;
            extern std::list<block> memFree;
            extern std::list<block> memAllocated;
            extern uint32_t chunkSize;
            extern void* lastAllocation;

            extern std::list<block>::iterator lastBlock;
            
        } // end of namespace mem

    } // end of namespace group

} // end of namespace somm22

#endif /* __SOMM22__MODULE__MEM__GROUP__ */


