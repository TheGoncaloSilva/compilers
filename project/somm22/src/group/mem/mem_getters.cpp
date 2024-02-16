/*
 *  \author Mauro Filho
 */

#include "somm22.h"
#include "mem_module.h"

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        uint32_t memGetBiggestHole()
        {
            soProbe(409, "%s()\n", __func__);

            if(mem::memFree.size() == 0) throw Exception(ENOMEM, "No allocated memory");
            
            uint32_t biggestSize = 0;
            for(auto it = mem::memFree.begin(); it != mem::memFree.end() ; it++)
            {
                uint32_t size = it->size;
                if(size > biggestSize)
                    biggestSize = size;
            }
			return biggestSize;
        }

// ================================================================================== //

        uint32_t memGetMaxAllowableSize()
        {
            soProbe(409, "%s()\n", __func__);
            return mem::maxSize;
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
