/*
 *  \author Mauro Filho
 */

#include "somm22.h"
#include "peq_module.h"

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        uint32_t peqGetFirstPostponedProcess() 
        {
            soProbe(306, "%s()\n", __func__);
            
            //Checks that queue is not empty
            if(peq::peq.size() == 0) return 0;
            
            for(std::list<Event>::iterator i = peq::peq.begin(); i != peq::peq.end(); i++){
				if(i->eventType == POSTPONED){
					return i->pid;
				}
			}
			return 0;
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

